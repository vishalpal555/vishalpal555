package com.vishal.skillvertexProject.User;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.catalina.filters.CorsFilter;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aj.org.objectweb.asm.Type;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<?> createUser (@RequestBody User user){

        if (user.getEmail().length() == 0 || user.getFirstName().length() == 0 || user.getLastName().length() == 0 || user.getPassword().length() == 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        
        if (userRepository.findByEmail(user.getEmail()).size() > 0){            
            return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
        }
        
        PasswordEncoder object = passwordEncoder();
        try {
            user.setPassword(object.encode(user.getPassword()));
        } catch (Exception e) {
            System.out.println("Problem encoding password ---> exception" +e);
        }
        

        user.setRole("USER");
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED) ;
    }

    @GetMapping("/{email}/{password}")
    @CrossOrigin
    public ResponseEntity<?> getUser(@PathVariable String email, @PathVariable String password){     
        if (userRepository.findByEmail(email).size() == 1){
            User requestUser = userRepository.findByEmail(email).get(0);
            PasswordEncoder object = passwordEncoder();
            if (object.matches(password, requestUser.getPassword())){
                
                return new ResponseEntity<>(userRepository.getNameByEmail(email), HttpStatus.ACCEPTED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            
            
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    
    


}
