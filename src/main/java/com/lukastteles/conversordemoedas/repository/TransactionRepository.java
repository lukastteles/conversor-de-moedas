package com.lukastteles.conversordemoedas.repository;

import com.lukastteles.conversordemoedas.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for {@link com.lukastteles.conversordemoedas.model.entity.Transaction} entity
 * @author Lukas Teles
 */
@Repository
public interface TransactionRepository extends  CrudRepository<Transaction, Long>{

    /**
     * Verify if there is a transaction for user id
     * @param userId {@link java.lang.Long} user id
     * @return boolean
     */
    boolean existsTransactionByUserId(Long userId);

    /**
     * Find all Transactions by user id
     * @param userId {@link java.lang.Long} user id
     * @return {@link java.util.List}
     */
    List<Transaction> findAllByUserId(Long userId);

}
