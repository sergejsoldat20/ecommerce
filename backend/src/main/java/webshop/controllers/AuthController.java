package webshop.controllers;


import org.apache.http.impl.client.BasicCookieStore;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import webshop.models.entities.AccountEntity;
import webshop.models.requests.Account;
import webshop.models.requests.AuthenticateRequest;
import webshop.models.responses.AuthenticateResponse;
import webshop.repositories.AccountRepository;
import webshop.security.JwtGenerator;
import webshop.security.SecurityConsts;
import webshop.services.AccountService;

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


    public AuthController(AuthenticationManager authenticationManager,
                          AccountService accountService,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator,
                          ModelMapper modelMapper,
                          AccountRepository repository) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account request) {

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

        // TODO: fix account confirmation
        request.setIsAccountConfirmed(true);

        // encode password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // TODO: send email for confirmation
        accountService.insert(request, AccountEntity.class);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
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
}
