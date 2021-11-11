package com.example.financeguysv1.model;

public class Usuario {
    private String uid ;
    private String nomeUsuario ;
    private String email ;
    private String password ;


    public Usuario(String uid , String nomeUsuario, String email, String password) {
        this.uid = uid;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.password = password;

    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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



    @Override
    public String toString() {
        return "Usuario{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\''  +
                '}';
    }
}
