package main.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest
{
    private String name;
    private String login;
    private String password;
    private List<Role> roles;
}