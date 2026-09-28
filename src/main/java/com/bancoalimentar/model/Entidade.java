package com.bancoalimentar.model;

public class Entidade {
    private Long id;
    private String nome;
    private TipoEntidade tipo;
    private String contacto;
    private String morada;
     private String email;

    // ENUMERADOR PARA TIPO ENTIDADE
    public enum TipoEntidade {
        DOADOR,
        BENEFICIARIO
    }

    // Construtores
    public Entidade() {}

    public Entidade(Long id, String nome, TipoEntidade tipo, String contacto) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.contacto = contacto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEntidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntidade tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}