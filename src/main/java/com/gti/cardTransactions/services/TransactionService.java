package com.gti.cardTransactions.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gti.cardTransactions.entities.Transaction;
import com.gti.cardTransactions.exceptions.PageNotFoundException;
import com.gti.cardTransactions.models.TransactionFilter;
import com.gti.cardTransactions.repositories.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
    private TransactionRepository transactionRepo;
	
	/**
	 * Récupère la liste de toutes les transactions enregistrées.
	 *
	 * @return Une liste contenant toutes les transactions enregistrées.
	 */
    public List<Transaction> getListTransactions() {
    	return transactionRepo.findAll();
    }
    
    /**
     * Récupère une liste filtrée de transactions en fonction des critères spécifiés.
     *
     * @param filter Le filtre de transactions pour appliquer les critères de filtrage.
     * @return Une liste contenant les transactions filtrées en fonction des critères du filtre.
     */
    public List<Transaction> getFiltredListTransactions(TransactionFilter filter) {
        
        return transactionRepo.findAll().stream()
                .filter(t -> filter.getAmount()== null || t.getAmount().equals(filter.getAmount())) // Filter par Amout si il existe
                .filter(t -> filter.getMerchant()== null || t.getMerchant().equals(filter.getMerchant())) // Filter par Merchant si il existe
                .filter(t -> filter.getStatus()== null || t.getStatus().equals(filter.getStatus())) // Filter par Status si il existe
                .toList();
    }
    
    /**
     * Récupère une liste de transactions triées par montant croissant.
     *
     * @return Une liste contenant les transactions triées par montant croissant.
     */    
    public List<Transaction> getSortedListByAmount() {
    	
        return transactionRepo.findAll().stream()
                .sorted((tr1, tr2) -> Double.compare(tr1.getAmount(), tr2.getAmount()))
                .toList();
    }
    
    /**
     * Récupère une liste de transactions triées par nom de merchant et statut.
     *
     * @return Une liste de transactions triées par nom de merchant et statut.
     */    
    public List<Transaction> getSortedListByMerchantAndStatus() {
    	List<Transaction> transactions = transactionRepo.findAll();
        Collections.sort(transactions, new TransactionComparator());
        return transactions;
    }
    
    /**
     * Récupère une page de transactions en fonction des paramètres de pagination fournis.
     *
     * @param pageable Les paramètres de pagination définissant la page à récupérer.
     * @return Une page de transactions basée sur les paramètres de pagination.
     * @throws PageNotFoundException Si le numéro de page demandé est invalide.
     */    
    public Page<Transaction> getListTransactionsByPage(Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), getListTransactions().size());

        if (start >= getListTransactions().size() || start > end) {
            throw new PageNotFoundException("Le nombre de page demandé n'existe pas");
        }        
        
        List<Transaction> sublist = getListTransactions().subList(start, end);

        return new PageImpl<>(sublist, pageable, getListTransactions().size());
    }
}
