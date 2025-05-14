package com.thomasgreg.clienteapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "InserirCliente",
                procedureName = "InserirCliente",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Nome", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Email", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Logo", type = byte[].class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "ListarTodosClientes",
                procedureName = "ListarTodosClientes",
                resultClasses = Cliente.class
        ),
        @NamedStoredProcedureQuery(
                name = "AtualizarCliente",
                procedureName = "AtualizarCliente",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Nome", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Email", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Logo", type = byte[].class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "BuscarClientePorId",
                procedureName = "BuscarClientePorId",
                resultClasses = Cliente.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "RemoverCliente",
                procedureName = "RemoverCliente",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Long.class)
                }
        )
})

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Nome",nullable = false)
    private String nome;

    @Column(name="Email",nullable = false, unique = true)
    private String email;

    @Lob
    @Column(name = "Logo")
    private byte[] logotipo;
}
