package webshop.models.requests;

import lombok.Data;

@Data
public class SendEmailRequest {
    public String email;
    public String message;
    public Integer messageId;
}
