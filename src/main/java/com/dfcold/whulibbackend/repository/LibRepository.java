package com.dfcold.whulibbackend.repository;

import com.dfcold.whulibbackend.entity.LibConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dfcold
 */
@Repository
@RepositoryRestResource
public interface LibRepository extends JpaRepository<LibConfig, Long> {
    /**
     * 通过用户id查找
     * @param userId 用户id
     * @return libConfig
     */
    List<LibConfig> findByUserId(Long userId);
}
