package webshop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.models.requests.ReportMessageRequest;
import webshop.models.requests.SendEmailRequest;
import webshop.models.responses.ReportMessageResponse;
import webshop.services.AccountService;
import webshop.services.ReportMessageService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/report-messages")
public class ReportMessageController {

    private final ReportMessageService reportMessageService;
    private final AccountService accountService;

    public ReportMessageController(ReportMessageService reportMessageService, AccountService accountService) {
        this.reportMessageService = reportMessageService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getMessagesByAccountId() {
        Integer id = accountService.getCurrentAccountId();
        return ResponseEntity.ok(reportMessageService.getReportMessagesByAccountId(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertReportMessage(@RequestBody ReportMessageRequest request) {
        reportMessageService.insertReportMessage(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/unread")
    public ResponseEntity<?> getUnreadMessages() {
        return ResponseEntity.ok(reportMessageService.getUnreadMessages());
    }

    @PostMapping("/admin/send-email")
    public ResponseEntity<?> sendEmailToUser(@RequestBody SendEmailRequest request) {
        reportMessageService.sendEmailToUser(request.getEmail(), request.getMessage(), request.getMessageId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getReportMessageById(@PathVariable Integer id) throws AppException {
        return ResponseEntity.ok(reportMessageService.getMessageById(id));
    }
}
