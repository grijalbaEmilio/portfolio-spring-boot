package com.example.springportfolio.services;

import com.example.springportfolio.exceptions.DuplicateException;
import com.example.springportfolio.exceptions.InvalidModifyData;
import com.example.springportfolio.exceptions.LengthException;
import com.example.springportfolio.exceptions.NotFoundResourceException;
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

    public UserService(UserRepository repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User  user) throws DuplicateException, LengthException {
        User userFind = repository.findByEmail(user.getEmail());
        if(userFind != null) throw new DuplicateException("email's user exists");
        if(user.getPassword().length() < 6) throw new LengthException("password must have min 6 characters");

        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        return repository.save(user);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id) throws NotFoundResourceException {
        Optional<User> userFind = repository.findById(id);

        if(userFind.isEmpty()) throw new NotFoundResourceException("user not found");
        return userFind.get();
    }

    public User deleteById(Long id) throws NotFoundResourceException {
        User userDelete = findById(id);
        repository.deleteById(id);
        return userDelete;
    }

    public  User update(User user) throws NotFoundResourceException, InvalidModifyData {
        User userFind = findById(user.getId());
        if(!userFind.getPassword().equals(user.getPassword())) throw new InvalidModifyData("can't be done password");

        return repository.save(user);
    }
}
