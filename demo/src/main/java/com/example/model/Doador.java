package com.bancoalimentar.model;

import java.util.ArrayList;
import java.util.List;

import main.java.com.example.model.Doacao;
import main.java.com.example.model.TipoDoador;

public class Doador {
     private String nome;
    private TipoDoador tipo;
    private String contacto; // Telefone 
    private String morada;

    private List<Doacao> historicoDoacoes = new ArrayList<>();


      public Doador() {

        public Doador(Long id, String nome, TipoDoador tipo, String contacto) // contrutor pros prametros obrigatorios
        {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.contacto = contacto;
        }
         @param Doacao
         public void adicionarDoacao(Doacao doacao) {
            this.historicoDoacoes.add(doacao);
            doacao.setDoador(this); 
         }

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
        
        public TipoDoador getTipo() {
            return tipo;
        }
        
        public void setTipo(TipoDoador tipo) {
            this.tipo = tipo;
        }
        
        public String getContacto() {
            return contacto;
        }
        
        public void setContacto(String contacto) {
            this.contacto = contacto;
        }
        
        public String getEndereco() {
            return endereco;
        }
        
        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }
        
        public List<Doacao> getHistoricoDoacoes() {
            return historicoDoacoes;
        }
        
        public void setHistoricoDoacoes(List<Doacao> historicoDoacoes) {
            this.historicoDoacoes = historicoDoacoes;
        }
        @return

    public Double totalDoado() {
    return this.historicoDoacoes.stream()
            .mapToDouble(Doacao::getVolumeTotal) 
            .sum();
}
         public Long getId() {
       return id;
    }
}