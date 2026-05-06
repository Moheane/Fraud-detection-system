package com.frauddetectionapp.Entities.account;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
@Builder
public class Account {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String userId;
    private String fullName;
    private String accountNumber;
    private String Branch;
}
