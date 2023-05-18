package kz.itstep.customauth.service;

import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.model.User;
import kz.itstep.customauth.repo.impl.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByLoginAndPassword(String login, String password) throws UserException {
        return userRepository.findUserByLoginAndPassword(login, password);
    }
    public boolean isExist(String login) throws UserException {
        return userRepository.findUserByLogin(login).isPresent();
    }
    public void saveUser(User user) throws UserException {
        userRepository.saveUser(user);
    }
}
