package com.gti.cardTransactions.repositories;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gti.cardTransactions.entities.Transaction;

@Repository
public class TransactionRepository{
    private final String jsonFilePath = "transactionsMock.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Transaction> findAll() {
        try {
            // Charge le fichier JSON à partir des ressources du chemin de classe
            ClassPathResource resource = new ClassPathResource(jsonFilePath);

            // Utilise ObjectMapper pour désérialiser le JSON en une liste de transactions
            return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Transaction>>() {});
        } catch (IOException e) {
            // En cas d'erreur lors de la lecture du fichier ou de désérialisation JSON
            e.printStackTrace();

            // Retourne une liste vide en cas d'erreur
            return Collections.emptyList();
        }
    }
}
