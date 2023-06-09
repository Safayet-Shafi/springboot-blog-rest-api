package com.springboot.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
        (name = "Comments",uniqueConstraints = {@UniqueConstraint(columnNames={"email"})})
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="body",nullable = false)
    private String body;



    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "post_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post post;
}
