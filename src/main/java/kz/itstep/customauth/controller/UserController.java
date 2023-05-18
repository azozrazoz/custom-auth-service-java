package kz.itstep.customauth.controller;

import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.model.User;
import kz.itstep.customauth.repo.impl.UserRepositoryImpl;
import kz.itstep.customauth.service.AuthorizationService;
import kz.itstep.customauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) throws UserException {
        if(user.getLogin()==null){
            return new ResponseEntity<>("no user", HttpStatus.NOT_FOUND);
        }
        else {
            userService.saveUser(user);
            return new ResponseEntity<>("your token is: " +
                    authorizationService.generateToken(user.getLogin()), HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<String> authorization(@RequestBody String login, @RequestBody String password)
            throws UserException {
        if (login.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userService.getByLoginAndPassword(login, password);
        if (user.isPresent()) {
            return new ResponseEntity<>(authorizationService.generateToken(login), HttpStatus.OK) ;
        }

        return new ResponseEntity<>("oops something went wrong", HttpStatus.BAD_REQUEST);
    }
}
