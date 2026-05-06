package com.frauddetectionapp.dto.user;

import com.frauddetectionapp.Entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;   // BCrypt-hashed — never plain text

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private User.Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }


    public void setUsername(String u)    { this.username = u; }

    public void setPassword(String p)    { this.password = p; }

    public void setRole(User.Role r)          { this.role = r; }
}

