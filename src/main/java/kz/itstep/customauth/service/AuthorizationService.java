package kz.itstep.customauth.service;

import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.repo.impl.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private static final Integer SHIFT = 6;
    private final UserRepositoryImpl userRepository;

    public AuthorizationService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
    public String generateToken(String login){
        return shepherLogin(login);
    }
    public boolean isValid(String token) throws UserException {
        return userRepository.findUserByLogin(deshepherLogin(token)).isPresent();
    }
    private String shepherLogin(String login){
        StringBuilder encryptedLogin = new StringBuilder();

        for (int i = 0; i < login.length(); i++) {
            char currentChar = login.charAt(i);

            if (Character.isLetter(currentChar)) {
                char baseChar = Character.isLowerCase(currentChar) ? 'a' : 'A';

                char encryptedChar = (char) (((currentChar - baseChar + SHIFT) % 26) + baseChar);

                encryptedLogin.append(encryptedChar);
            }
            else {
                encryptedLogin.append(currentChar);
            }
        }

        return encryptedLogin.toString();
    }
    public static String deshepherLogin(String encryptedLogin) {
        StringBuilder decryptedLogin = new StringBuilder();

        for (int i = 0; i < encryptedLogin.length(); i++) {
            char currentChar = encryptedLogin.charAt(i);

            if (Character.isLetter(currentChar)) {
                char baseChar = Character.isLowerCase(currentChar) ? 'a' : 'A';
                char decryptedChar = (char) (((currentChar - baseChar - SHIFT + 26) % 26) + baseChar);
                decryptedLogin.append(decryptedChar);
            }
            else {
                decryptedLogin.append(currentChar);
            }
        }

        return decryptedLogin.toString();
    }
}
