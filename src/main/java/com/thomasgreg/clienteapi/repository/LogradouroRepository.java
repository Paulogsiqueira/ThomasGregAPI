package com.thomasgreg.clienteapi.repository;

import com.thomasgreg.clienteapi.entity.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

    @Query(
            value = "EXEC InserirLogradouro :ClienteId, :Rua, :Numero, :Bairro, :Cidade, :Estado",
            nativeQuery = true
    )
    Logradouro inserirLogradouro(
            @Param("ClienteId") Long clienteId,
            @Param("Rua") String rua,
            @Param("Numero") String numero,
            @Param("Bairro") String bairro,
            @Param("Cidade") String cidade,
            @Param("Estado") String estado
    );

    @Query(
            value = "EXEC AtualizarLogradouro :Id, :ClienteId, :Rua, :Numero, :Bairro, :Cidade, :Estado",
            nativeQuery = true
    )
    @Transactional
    Logradouro atualizarLogradouro(
            @Param("Id") Long id,
            @Param("ClienteId") Long clienteId,
            @Param("Rua") String rua,
            @Param("Numero") String numero,
            @Param("Bairro") String bairro,
            @Param("Cidade") String cidade,
            @Param("Estado") String estado
    );

    @Procedure(name = "BuscarLogradourosPorCliente")
    List<Logradouro> buscarLogradourosPorCliente(@Param("ClienteId") Long clienteId);

    @Procedure(name = "RemoverLogradouro")
    void removerLogradouro(@Param("Id") Long id);
}