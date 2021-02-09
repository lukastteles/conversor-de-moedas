package com.lukastteles.conversordemoedas.repository;

import com.lukastteles.conversordemoedas.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link com.lukastteles.conversordemoedas.model.entity.User} entity
 * @author Lukas Teles
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
