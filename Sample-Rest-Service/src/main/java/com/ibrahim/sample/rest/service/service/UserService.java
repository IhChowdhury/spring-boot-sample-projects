package com.ibrahim.sample.rest.service.service;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.exception.UserAlreadyExistException;
import com.ibrahim.sample.rest.service.model.entity.User;
import com.ibrahim.sample.rest.service.model.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    Map<String, User> userMap = new ConcurrentHashMap<>();

    public User addNewUser(UserRequest userRequest) throws UserAlreadyExistException {
        String firstName = userRequest.getFirstName();
        String lastName = userRequest.getLastName();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        if (userMap.containsKey(email)) {
            throw new UserAlreadyExistException(String.format("%s user already exist!", email));
        }
        String encryptPassword = passwordEncoder.encode(password);
        User newUser = new User(firstName, lastName, email, encryptPassword);
        userMap.put(email, newUser);

        return newUser;
    }

    public User getUserByEmail(String email) throws ResourceNotFoundException {
        if(userMap.containsKey(email)){
            return userMap.get(email);
        }
        throw new ResourceNotFoundException(String.format("%s user not found!", email));
    }
}
