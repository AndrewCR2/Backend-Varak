package com.mantenimiento.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mantenimiento.spring.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    // @Query(value = "SELECT a FROM Cliente a WHERE a.nombre=?1")
    // public List<Cliente> buscarClientesPorNombre(String nombre);

    // @Query(value = "SELECT a FROM Cliente a WHERE a.nombre like CONCAT(?1,'%')")
    // public List<Cliente> buscarClienteLikeNombre(String nombre);
}