package com.example.customersupport.dto;

public class SendEmailRequest {
    public String email;
    public String message;
    public Integer messageId;

    public SendEmailRequest(String email, String message, Integer messageId) {
        this.email = email;
        this.message = message;
        this.messageId = messageId;
    }
}
