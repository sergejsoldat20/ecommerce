package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.util.Objects;

@Data
@Entity
@jakarta.persistence.Table(name = "account", schema = "public", catalog = "ecommerce_db")
public class AccountEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "role")
    private String role;

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "is_account_confirmed")
    private Boolean isAccountConfirmed;

    public Boolean getAccountConfirmed() {
        return isAccountConfirmed;
    }

    public void setAccountConfirmed(Boolean accountConfirmed) {
        isAccountConfirmed = accountConfirmed;
    }

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "location")
    private String location;

}
