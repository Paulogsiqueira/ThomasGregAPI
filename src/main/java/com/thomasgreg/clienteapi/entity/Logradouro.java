package com.thomasgreg.clienteapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "InserirLogradouro",
                procedureName = "InserirLogradouro",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ClienteId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Rua", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Numero", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Bairro", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Cidade", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Estado", type = String.class),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "AtualizarLogradouro",
                procedureName = "AtualizarLogradouro",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ClienteId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Rua", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Numero", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Bairro", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Cidade", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Estado", type = String.class),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "BuscarLogradourosPorCliente.sql",
                procedureName = "BuscarLogradourosPorCliente.sql",
                resultClasses = Logradouro.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ClienteId", type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "RemoverLogradouro",
                procedureName = "RemoverLogradouro",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Long.class)
                }
        )
})


public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Rua")
    private String rua;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Bairro")
    private String bairro;

    @Column(name = "Cidade")
    private String cidade;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "ClienteId")
    private Long clienteId;

}