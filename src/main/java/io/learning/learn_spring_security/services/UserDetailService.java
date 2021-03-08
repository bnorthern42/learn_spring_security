package io.learning.learn_spring_security.services;

import io.learning.learn_spring_security.interfaces.UserRepository;
import io.learning.learn_spring_security.models.Users;
import io.learning.learn_spring_security.models.appUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      Optional<Users> user = userRepository.findByUserName(userName); //optional as some brute force may not exist

        user.orElseThrow( () -> new UsernameNotFoundException("Not found: " + userName)); //if username not found, throw exception

       return user.map(appUserDetails::new).get();
    }
}
