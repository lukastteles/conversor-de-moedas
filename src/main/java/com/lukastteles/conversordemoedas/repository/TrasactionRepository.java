package com.lukastteles.conversordemoedas.repository;

import com.lukastteles.conversordemoedas.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasactionRepository extends  CrudRepository<Transaction, Long>{

}
