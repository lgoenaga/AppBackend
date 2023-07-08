package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.Role;
import com.iudigital.appbackend.model.Status;
import com.iudigital.appbackend.model.User;
import com.iudigital.appbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static final String SUCCESSFULLY = "Process done correctly";

    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return  userRepository.getUserById(id);

    }

    public String createUser(User user) {
        userRepository.save(user);
        return SUCCESSFULLY;
    }
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return SUCCESSFULLY;
    }

    public boolean existUserById(Long id){
        return userRepository.existsById(id);
    }

    public boolean existUserByName(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existUserByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existUserNameOrEmail(String username, String email){

        boolean exist = userRepository.existsByEmail(email);

        if (userRepository.existsByUsername(username)){
            exist = true;
        }

      return exist;
    }

  public Role role(Role role) {
      if (role == null) {
          role = Role.USER;
      }
        return role;
    }
  public Status status(Status status) {

        if (status == null) {
            status = Status.ACTIVE;
        }

        return status;

    }
}
