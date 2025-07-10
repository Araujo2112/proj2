package com.example.projetov2.service;

import com.example.projetov2.model.CodPostal;
import com.example.projetov2.repository.CodPostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Services são responsáveis pela lógica da aplicação
@Service
public class CodPostalService {
    // Repositório usado para aceder os dados do CodPostal na BD
    private final CodPostalRepository codPostalRepository;

    // Injeção do repositório via construtor pelo Spring (@Autowired faz isso automaticamente)
    @Autowired
    public CodPostalService(CodPostalRepository codPostalRepository) {
        this.codPostalRepository = codPostalRepository;
    }

    // Método para obter todos os códigos postais (lista completa)
    public List<CodPostal> getAllCodPostais() {
        // Chama o método findAll() do repositório, que retorna todos os registros
        return codPostalRepository.findAll();
    }

    // Método para procurar um código postal pelo seu ID (string)
    public Optional<CodPostal> getCodPostalById(String idCodPostal) {
        // Retorna um Optional, que pode conter um CodPostal ou estar vazio se não existir
        return codPostalRepository.findById(idCodPostal);
    }

    // Método para criar um novo código postal e guardar na BD
    public CodPostal createCodPostal(CodPostal codPostal) {
        // Guarda o objeto CodPostal passado e retorna o objeto salvo
        return codPostalRepository.save(codPostal);
    }

    // Método para atualizar um código postal existente pelo ID
    public CodPostal updateCodPostal(String idCodPostal, CodPostal updatedCodPostal) {
        // Primeiro tenta procurar o código postal pelo ID
        return codPostalRepository.findById(idCodPostal).map(existingCodPostal -> {
            // Se encontrar, atualiza os dados desejados
            existingCodPostal.setLocalidade(updatedCodPostal.getLocalidade());
            // Guarda a.html entidade atualizada na BD e retorna
            return codPostalRepository.save(existingCodPostal);
        }).orElseThrow(() -> new RuntimeException("Código Postal não encontrado!"));
        // Se não encontrar o código postal, lança uma exceção a.html informar que não existe
    }

    // Método para eliminar um código postal pelo ID
    public void deleteCodPostal(String idCodPostal) {
        // Chama o método deleteById() do repositório para remover da BD
        codPostalRepository.deleteById(idCodPostal);
    }
}

