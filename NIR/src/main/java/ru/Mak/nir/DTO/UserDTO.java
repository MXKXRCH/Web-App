package ru.Mak.nir.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.Mak.nir.entities.Role;
import ru.Mak.nir.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserDTO extends BaseDTO {
    @NotNull
    @Size(min=3, max=64, message = "Minimum symbols: 3\nMaximum symbols: 64")
    private String name;
    private List<String> roles;

    public UserDTO(User user) {
        this.setId(user.getId());
        this.name = user.getName();
        this.roles = new ArrayList<String>(user.getRoles().size());
        for (Role role : user.getRoles())
            this.roles.add(role.toString());
    }
}
