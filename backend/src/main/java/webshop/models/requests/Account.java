package webshop.models.requests;


import lombok.Data;

@Data
public class Account {
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String phoneNumber;
    private String email;
    private Boolean isAccountConfirmed;
    private String password;
    private String location;
}
