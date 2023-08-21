package webshop.controllers;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.mail.EmailService;
import webshop.models.entities.AccountEntity;
import webshop.models.requests.Account;
import webshop.models.requests.AuthenticateRequest;
import webshop.models.requests.ConfirmEmailRequest;
import webshop.models.responses.AccountResponse;
import webshop.models.responses.AuthenticateResponse;
import webshop.repositories.AccountRepository;
import webshop.security.JwtGenerator;
import webshop.security.SecurityConsts;
import webshop.services.AccountService;

import java.util.HashMap;
import java.util.Random;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final ModelMapper modelMapper;
    private final AccountRepository repository;
    private final EmailService emailService;
    private static HashMap<String, String> usernameConfirmationPinMap = new HashMap<>();

    public AuthController(AuthenticationManager authenticationManager,
                          AccountService accountService,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator,
                          ModelMapper modelMapper,
                          AccountRepository repository,
                          EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account request) {

        if(repository.existsByUsername(request.getUsername())
                || repository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Username or email is taken!", HttpStatus.BAD_REQUEST);
        }

        // first registered user will be admin and others will be users
        if(accountService.getAllAccounts().size() == 0) {
            request.setRole(SecurityConsts.ADMIN);
        } else {
            request.setRole(SecurityConsts.USER);
        }

        request.setIsAccountConfirmed(false);

        // encode password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // TODO: send email for confirmation
        AccountResponse response = accountService.insert(request, AccountResponse.class);

        String randomPin = String.format("%06d", new Random().nextInt(1000000));
        usernameConfirmationPinMap.put(request.getUsername(), randomPin);
        emailService.sendEmail(request.getEmail(), "Internet programiranje - konfirmacija naloga", "Vas pin je: " + randomPin);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        // System.out.println(token);
        return new ResponseEntity<>(new AuthenticateResponse(token), HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateUser(HttpServletRequest request) throws AppException {
        String token = request.getHeader("Authorization");
        String secret = SecurityConsts.JWT_SECRET_KEY;

        // parse jwt token
        Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.substring(7));
        Claims claims = jws.getBody();

        // Extract data from claims
        String username = claims.getSubject();
        Integer userId = claims.get("id", Integer.class);
        AccountEntity account = accountService.findEntityById(userId);
        return ResponseEntity.ok(modelMapper.map(account, AccountResponse.class));
    }

    @PostMapping("/confirm-pin/{username}")
    public ResponseEntity<?> confirmPin(@PathVariable String username,
                                        @RequestBody ConfirmEmailRequest request) throws AppException {

        AccountEntity account = accountService.getAccountByUsername(username);
        if(account == null) {
            return new ResponseEntity<>("Account with username " + username + " does not exist!", HttpStatus.BAD_REQUEST);
        }
        String pin = usernameConfirmationPinMap.get(username);


        if(pin.equals(request.getPin())) {
            account.setIsAccountConfirmed(true);
            accountService.update(account.getId(), account, AccountResponse.class);
            return ResponseEntity.ok("Account confirmed!");
        } else {
            return new ResponseEntity<>("Pin is not correct!", HttpStatus.BAD_REQUEST);
        }
    }
}
