package com.sandbox.email.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "email")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @GeneratedValue
    @Id
    private int id;
    private String email;
    private int code;
}
