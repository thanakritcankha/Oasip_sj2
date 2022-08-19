package oasip.backend.repositories;

import oasip.backend.Enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}