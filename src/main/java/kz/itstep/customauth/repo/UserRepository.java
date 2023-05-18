package kz.itstep.customauth.repo;

import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws UserException;
    Optional<User> findUserByLogin(String login) throws UserException;
    void saveUser(User user) throws UserException;
}
