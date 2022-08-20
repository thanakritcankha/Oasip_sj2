package oasip.backend.repositories;

import oasip.backend.Enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
=======
    User findByName(String name);
    User findByEmail(String name);
>>>>>>> dev044
}