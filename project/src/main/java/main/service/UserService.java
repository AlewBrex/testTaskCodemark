package main.service;

import lombok.extern.log4j.Log4j2;
import main.api.request.UserRequest;
import main.api.response.UserWithRoleResponse;
import main.api.response.success.BadResponse;
import main.api.response.success.OkResponse;
import main.model.Role;
import main.model.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity getAllUser()
    {
        if (userRepository.findAll().isEmpty())
        {
            log.info("list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<User> userList = userRepository.findAll();
        log.info("return list users");
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    public ResponseEntity getUserById(int id)
    {
        User user = userRepository.getOne(id);
        if (user == null)
        {
            log.info("message");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        UserWithRoleResponse userWithRoleResponse =
                new UserWithRoleResponse(user.getName(),
                        user.getLogin(), user.getPassword(), user.getRoles());
        log.info("message");
        return new ResponseEntity(userWithRoleResponse, HttpStatus.OK);
    }

    public ResponseEntity deleteUserById(int id)
    {
        if (!userRepository.existsById(id))
        {
            log.info("user not exists");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        log.info("user deleted");
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity addUser(UserRequest userRequest)
    {
        String name = userRequest.getName();
        String login = userRequest.getLogin();
        String password = userRequest.getPassword();
        List<Role> list = userRequest.getRoles();

        String regex = "(?=\\d)(?=[A-Z])";
        List<String> errors = new ArrayList<>();

        if (!(name.isBlank() && login.isBlank() && password.isBlank()))
        {
            if (password.matches(regex))
            {
                if (userRepository.findByLogin(login) == null)
                {
                    User user = new User();
                    user.setName(name);
                    user.setLogin(login);
                    user.setPassword(password);

                    if (!list.isEmpty())
                    {
                        user.setRoles(list);
                    }
                    userRepository.saveAndFlush(user);
                    log.info("message");
                    return new ResponseEntity(new OkResponse(true), HttpStatus.OK);
                }
                log.info("message");
                errors.add("login already exist");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            log.info("message");
            errors.add("uncorrected password");
            return new ResponseEntity(new BadResponse(false, errors), HttpStatus.BAD_REQUEST);
        }
        log.info("message");
        errors.add("empty fields");
        return new ResponseEntity(new BadResponse(false, errors), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity editUser(int id, UserRequest userRequest)
    {
        User user = userRepository.getOne(id);
        List<String> errors = new ArrayList<>();
        if (user == null)
        {
            log.info("message");
            errors.add("no user");
            return new ResponseEntity(new BadResponse(false, errors),HttpStatus.BAD_REQUEST);
        }

        String name = userRequest.getName();
        String login = userRequest.getLogin();
        String password = userRequest.getPassword();
        List<Role> list = userRequest.getRoles();

        String regex = "(?=\\d)(?=[A-Z])";

        if (!(name.isBlank() && login.isBlank() && password.isBlank()))
        {
            if (password.matches(regex))
            {
                if (userRepository.findByLogin(login) == null)
                {
                    user.setName(name);
                    user.setLogin(login);
                    user.setPassword(password);

                    if (!list.isEmpty())
                    {
                        user.setRoles(null);
                        user.setRoles(list);
                    }
                    userRepository.saveAndFlush(user);
                    log.info("message");
                    return new ResponseEntity(new OkResponse(true), HttpStatus.OK);
                }
                log.info("message");
                errors.add("login already exist");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            log.info("message");
            errors.add("uncorrected password");
            return new ResponseEntity(new BadResponse(false, errors), HttpStatus.BAD_REQUEST);
        }
        log.info("message");
        errors.add("empty fields");
        return new ResponseEntity(new BadResponse(false, errors), HttpStatus.NOT_FOUND);
    }
}