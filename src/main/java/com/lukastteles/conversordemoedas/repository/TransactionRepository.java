package com.lukastteles.conversordemoedas.repository;

import com.lukastteles.conversordemoedas.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends  CrudRepository<Transaction, Long>{

    boolean existsTransactionByUserId(Long userId);
    List<Transaction> findAllByUserId(Long userId);

}
