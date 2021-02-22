package com.example.alice.Dto;

import com.example.alice.model.Usuario;

public class AutenticaçãoUsuario {

    private String tipo;
    private String email;
    private String nome;

    public AutenticaçãoUsuario(String email, String nome, String tipo) {

        this.email = email;
        this.nome = nome;
        this.tipo = tipo;
    }

    public AutenticaçãoUsuario(){}

    public static AutenticaçãoUsuario toDTO(Usuario usuario, String tipo) {
        return new AutenticaçãoUsuario( usuario.getEmail(), usuario.getNome(), tipo);
    }
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }


    public String getTipo() {
        return tipo;
    }
}


