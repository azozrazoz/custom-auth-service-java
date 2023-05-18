package kz.itstep.customauth.controller;

import kz.itstep.customauth.model.User;
import kz.itstep.customauth.repo.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user){
        if(user.getLogin()==null){
            return new ResponseEntity<>("no user", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>("your token is: ", HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<String> authorization(@RequestBody String login, @RequestBody String password) {
        if (login.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
        User user =


        return null;
    }
}
