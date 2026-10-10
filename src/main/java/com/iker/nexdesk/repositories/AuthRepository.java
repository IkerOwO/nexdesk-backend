package com.iker.nexdesk.repositories;

import com.iker.nexdesk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author IkerOwO
 * <p>
 *    Auth Repository
 * </p>
 */
public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
