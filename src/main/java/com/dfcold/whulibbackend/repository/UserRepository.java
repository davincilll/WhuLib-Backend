package com.dfcold.whulibbackend.repository;

import com.dfcold.whulibbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author dfcold
 */
@Repository
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
}
