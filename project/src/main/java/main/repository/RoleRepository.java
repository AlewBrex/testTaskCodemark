package main.repository;

import main.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    List<Role> findAll();
    Role getOne(Integer integer);
}