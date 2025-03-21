package com.example.projetov2.repository;

import com.example.projetov2.model.CodPostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodPostalRepository extends JpaRepository<CodPostal, String> {
}
