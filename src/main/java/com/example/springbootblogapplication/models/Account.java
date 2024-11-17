package com.example.springbootblogapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true)
    String email;

    @JsonIgnore
    String password;

    String firstName;

    String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority", // Nazwa tabeli łączącej konto z rolami
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")}, // Kolumna związana z kontem
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}) // Kolumna związana z rolą
    Set<Authority> authorities = new HashSet<>(); // Dostep do rol

}
