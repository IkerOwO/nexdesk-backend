package com.iker.nexdesk.repositories;

import com.iker.nexdesk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author IkerOwO
 * <p>
 *    Auth Repository
 * </p>
 */
public interface AuthRepository extends JpaRepository<User, Long> {

}
