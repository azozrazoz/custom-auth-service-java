package kz.itstep.customauth.controller;

import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    AuthorizationService authorizationService;

    public InfoController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public ResponseEntity<String> getInfo(@RequestHeader(value = "Authorization", required = false) String token)
            throws UserException {

        if(token!=null && authorizationService.isValid(token)){
            return ResponseEntity.ok("This is protected info for authorized users");
        }
        else {
            return new ResponseEntity<>("You are not allow to do this", HttpStatus.FORBIDDEN);
        }
    }
}
