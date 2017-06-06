package org.constantgatherer.persistence;


import org.constantgatherer.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository Class.
 * User: ggomes
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findUserByEmail(String email);
}
