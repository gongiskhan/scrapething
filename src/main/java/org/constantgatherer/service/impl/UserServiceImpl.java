package org.constantgatherer.service.impl;

import org.constantgatherer.error.ExceptionFactory;
import org.constantgatherer.error.GathererException;
import org.constantgatherer.model.User;
import org.constantgatherer.persistence.UserRepository;
import org.constantgatherer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * Demo Project documentation not available
 * User: ggomes
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExceptionFactory exceptionFactory;

    @Override
    public User persist(User user) throws UnsupportedEncodingException, GeneralSecurityException, GathererException {

        //if the user already has an id then this is an update and we need to keep the encrypted password when a new one is not provided.
        if(user.getId() != null && user.getId() > 0 && (user.getPassword() == null || user.getPassword().isEmpty())){
            User storedUser = userRepository.findOne(user.getId());
            user.setPassword(storedUser.getPassword());
        }else{
            user.setPassword(new ShaPasswordEncoder().encodePassword(user.getPassword(),""));
        }

        User savedUser;
        try{
            savedUser = userRepository.save(user);
        }catch(Exception ex){
            throw exceptionFactory.throwException(ex);
        }
        return savedUser;
    }

    @Override
    public User fetch(Long id) {
        User user =  userRepository.findOne(id);
        if(user != null)
            user.setPassword(null);
        return user;
    }

    @Override
    public User fetchByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user != null)
            user.setPassword(null);
        return user;
    }

    @Override
    public List<User> fetchAll() {
        List<User> users = (List<User>)userRepository.findAll();
        for(User user : users){
            user.setPassword(null);
        }
        return users;
    }

    @Override
    public User delete(Long id) {
        User user = fetch(id);
        if(user != null)
            userRepository.delete(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username);
    }

    @Override
    public UserDetails loadUserDetails(Authentication auth) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(auth.getPrincipal().toString());
    }
}
