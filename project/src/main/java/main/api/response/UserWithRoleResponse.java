package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleResponse extends UserResponse
{
    private List<Role> roles;

    public UserWithRoleResponse(String name, String login, String password, List<Role> roles) {
        super(name, login, password);
        this.roles = roles;
    }
}
