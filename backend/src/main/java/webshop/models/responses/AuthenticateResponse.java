package webshop.models.responses;

import lombok.Data;
import webshop.models.requests.AuthenticateRequest;

@Data
public class AuthenticateResponse {
    private String token;

    public AuthenticateResponse(String token) {
        this.token = token;
    }
}
