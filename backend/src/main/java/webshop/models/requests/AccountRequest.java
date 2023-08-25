package webshop.models.requests;

import lombok.Data;

@Data
public class AccountRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String phoneNumber;
    private String location;
}
