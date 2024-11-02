package com.sandbox.paging.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "article")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    String id;
    String title;
    String createdAt;
}
