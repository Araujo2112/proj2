package com.example.projetov2.repository;

// Importa a.html classe do model CodPostal para que possamos usá-la aqui
import com.example.projetov2.model.CodPostal;
// Importa a.html interface JpaRepository do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;
// Importa a.html anotação Repository do Spring
import org.springframework.stereotype.Repository;

// Anotação que indica que essa interface é um componente de repositório do Spring
// Ela será detectada automaticamente e usada para injetar instâncias onde for necessário
@Repository
// JpaRepository recebe dois parâmetros: a.html entidade que será gerenciada (CodPostal)
// e o tipo da chave primária da entidade
public interface CodPostalRepository extends JpaRepository<CodPostal, String> {
}
