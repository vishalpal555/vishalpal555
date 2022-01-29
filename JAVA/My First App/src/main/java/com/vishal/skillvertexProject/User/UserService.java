package com.vishal.skillvertexProject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{


    @Autowired
        UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        if(userRepository.getNameByEmail(username).length() == 0){
            throw new UsernameNotFoundException(username +" : is not present");
        }
    
        return userRepository.findByEmail(username).get(0);
    }
    
}
