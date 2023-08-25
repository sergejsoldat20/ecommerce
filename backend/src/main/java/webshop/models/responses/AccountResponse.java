package webshop.models.responses;

import lombok.Data;

@Data
public class AccountResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String email;
    private String location;
    private String role;
    private Boolean isAccountConfirmed;
}
