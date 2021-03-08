package io.learning.learn_spring_security.interfaces;

import io.learning.learn_spring_security.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByUserName(String userName);

}
