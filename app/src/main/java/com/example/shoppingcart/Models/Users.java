package com.example.shoppingcart.Models;

public class Users
{
    String name,email,password,ref_code;

    public Users(String name, String email, String password, String ref_code) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ref_code = ref_code;
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRef_code() {
        return ref_code;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }
}
