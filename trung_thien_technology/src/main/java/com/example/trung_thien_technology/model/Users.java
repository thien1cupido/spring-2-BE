package com.example.trung_thien_technology.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(name = "verification_code")
    private Integer verificationCode;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;
    @Column(length = 50)
    private String email;

    public Users() {
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users(Long id, String username, String password, Integer verificationCode,Roles roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.verificationCode = verificationCode;
        this.roles=roles;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }




    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
