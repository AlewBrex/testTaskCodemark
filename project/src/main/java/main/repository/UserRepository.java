package main.repository;

import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User getOne(Integer integer);

    List<User> findAll();

    User saveAndFlush(User user);

    User findByLogin(String login);

    void deleteById(Integer integer);
}