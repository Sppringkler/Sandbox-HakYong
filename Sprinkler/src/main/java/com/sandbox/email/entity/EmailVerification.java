package com.sandbox.email.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "email")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EmailVerification {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String email;
    private String code;

}
