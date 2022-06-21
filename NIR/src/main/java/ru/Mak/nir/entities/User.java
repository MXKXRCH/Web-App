package ru.Mak.nir.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table (name = "userEntity")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "userName")
    private String userName;
    @Column (name = "userPass")
    private String userPass;
    @ElementCollection (targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable (name = "user_role", joinColumns = @JoinColumn (name = "user_id"))
    @Enumerated (EnumType.STRING)
    private Set<Role> roles;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<Operation> userOperations = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<RepeatedOperation> userRepeatedOperations = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<Goal> goalEntities = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<Tag> tags = new HashSet<>();

    public String getUserPassHash() {
        return this.userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public boolean isValidPassword(String pass) {
        return BCrypt.checkpw(pass, this.userPass);
    }
}
