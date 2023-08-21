package webshop.models.requests;

import lombok.Data;

@Data
public class ConfirmEmailRequest {
    private String pin;
}
