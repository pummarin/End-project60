package com.example.voting.entity;

import lombok.Data;


@Data
@Entity
@Table(name = "ADMIN")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMIN_ID", insertable = true)
    private long id;

    private @NotNull String name;
    private @NotNull String username;
    private @NotNull String password;
    private @NotNull String gender;


    public Staff() {
    }

    public Staff(String name, String user, String pass, String gender) {
        this.name = name;
        this.password = pass;
        this.username = user;
        this.gender = gender;
    }
}