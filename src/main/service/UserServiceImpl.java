package main.service;

import main.model.Role;
import main.model.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void createNewAccount(User user) {
        user.setEnabled(true);
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);


        Role role = new Role();
        role.setLogin(user.getLogin());
        role.setRole("ROLE_CLIENT");
        roleRepository.save(role);
    }

    @Override
    public boolean loginExists(String login) {
        return  userRepository.existsBylogin(login);
    }


}
