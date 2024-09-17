package com.wprotheus.pdm2atvpratica29jun2024_parte2.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Partida implements Serializable {
    private String id;
    private String nome;
    private String numeros;
    private String soma;
    private String resultado;
    private String imagem;
}