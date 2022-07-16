package ru.Mak.nir.entities;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import ru.Mak.nir.DTO.UserDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "user")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class User extends Base {
    @Column (name = "name")
    private String name;
    @Column (name = "password")
    private String pass;
    @ElementCollection (targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable (name = "role", joinColumns = @JoinColumn (name = "user_id"))
    @Enumerated (EnumType.STRING)
    private Set<Role> roles;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Operation> userOperations;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<RepeatedOperation> userRepeatedOperations;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Goal> goalEntities;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Tag> tags;

    public User(UserDTO userDTO, String password) {
        this.setId(userDTO.getId());
        this.name = userDTO.getName();
        this.setUserPass(password);
        try {
            for (String s : userToDTO().getRoles()) {
                roles.add(Role.valueOf(s));
            }
        } catch (IllegalArgumentException e) {
            roles.clear();
            roles.add(Role.USER);
        }
    }

    public UserDTO userToDTO() {
        return new UserDTO(this);
    }

    public String getUserPassHash() {
        return this.pass;
    }

    public void setUserPass(String userPass) {
        this.pass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public boolean isValidPassword(String pass) {
        return BCrypt.checkpw(pass, this.pass);
    }
}
