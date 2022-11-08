package com.example.springportfolio.services;

import com.example.springportfolio.dto.ResponseLogin;
import com.example.springportfolio.exceptions.*;
import com.example.springportfolio.model.User;
import com.example.springportfolio.reposiroties.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository repository;
    PasswordEncoder passwordEncoder;
    JWTUserService jwtUserService;

    public UserService(UserRepository repository, JWTUserService jwtUserService){
        this.repository = repository;
        this.jwtUserService = jwtUserService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User register(User  user) throws DuplicateException, LengthException {
        User userFind = repository.findByEmail(user.getEmail());
        if(userFind != null) throw new DuplicateException("email's user exists");
        if(user.getPassword().length() < 6) throw new LengthException("password must have min 6 characters");

        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        return repository.save(user);
    }

    public ResponseLogin login(String email, String password) throws NotFoundResourceException, InvalidPasswordException {
        User user = repository.findByEmail(email);
        if(user == null) throw new NotFoundResourceException("user not found");

        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());

        if(!passwordMatch) throw new InvalidPasswordException("incorrect password");

        String accessToken = jwtUserService.generateAccessToken(user);

        return new ResponseLogin(user, accessToken);
    }

    public List<User> findAll(String token) throws NotAuthorizedException {
        jwtUserService.adminValidateAccessToken(token);
        return repository.findAll();
    }

    public User findById(Long id, String token) throws NotFoundResourceException, NotAuthorizedException {
        jwtUserService.adminValidateAccessToken(token);
        Optional<User> userFind = repository.findById(id);


        if(userFind.isEmpty()) throw new NotFoundResourceException("user not found");
        return userFind.get();
    }

    public User deleteById(Long id, String token) throws NotFoundResourceException, NotAuthorizedException {
        User userDelete = findById(id, token);


        repository.deleteById(id);
        return userDelete;
    }

    public  User update(User user, String token) throws NotFoundResourceException, NotAuthorizedException {
        User userFind = findById(user.getId(), token);
        user.setPassword(userFind.getPassword());

        return repository.save(user);
    }
}
