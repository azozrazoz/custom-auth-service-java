package kz.itstep.customauth.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final Integer SHIFT = 6;
    public String generateToken(String login){

        return null;
    }
    public boolean isValid(String token) {
        return true;
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
}
