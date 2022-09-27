package com.ema.app.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    public final Integer id;
    public final Double latitude;
    public final Double longitude;
    public final String uf;
    public final String cidade;
    public final String bairro;
    public final String endereco;
    public final String cep;
    public final String categoria;

    public Address(@JsonProperty("id") Integer id,
                   @JsonProperty("latitude") Double latitude,
                   @JsonProperty("longitude") Double longitude,
                   @JsonProperty("uf") String uf,
                   @JsonProperty("cidade") String cidade,
                   @JsonProperty("bairro") String bairro,
                   @JsonProperty("endereco") String endereco,
                   @JsonProperty("cep") String cep,
                   @JsonProperty("categoria") String categoria) {
                        this.id = id;
                        this.latitude = latitude;
                        this.longitude = longitude;
                        this.uf = uf;
                        this.cidade = cidade;
                        this.bairro = bairro;
                        this.endereco = endereco;
                        this.cep = cep;
                        this.categoria = categoria;
                    }
}