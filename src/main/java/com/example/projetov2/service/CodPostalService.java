package com.example.projetov2.service;

import com.example.projetov2.model.CodPostal;
import com.example.projetov2.repository.CodPostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodPostalService {

    private final CodPostalRepository codPostalRepository;

    @Autowired
    public CodPostalService(CodPostalRepository codPostalRepository) {
        this.codPostalRepository = codPostalRepository;
    }

    // Listar todos os códigos postais
    public List<CodPostal> getAllCodPostais() {
        return codPostalRepository.findAll();
    }

    // Buscar um código postal específico
    public Optional<CodPostal> getCodPostalById(String idCodPostal) {
        return codPostalRepository.findById(idCodPostal);
    }

    // Criar um novo código postal
    public CodPostal createCodPostal(CodPostal codPostal) {
        return codPostalRepository.save(codPostal);
    }

    // Atualizar um código postal existente
    public CodPostal updateCodPostal(String idCodPostal, CodPostal updatedCodPostal) {
        return codPostalRepository.findById(idCodPostal).map(existingCodPostal -> {
            existingCodPostal.setLocalidade(updatedCodPostal.getLocalidade());
            return codPostalRepository.save(existingCodPostal);
        }).orElseThrow(() -> new RuntimeException("Código Postal não encontrado!"));
    }

    // Deletar um código postal
    public void deleteCodPostal(String idCodPostal) {
        codPostalRepository.deleteById(idCodPostal);
    }
}

