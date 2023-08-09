package webshop.models.requests;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String email;
    private String password;
}
