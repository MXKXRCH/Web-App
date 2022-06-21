package ru.Mak.nir.services;

import ru.Mak.nir.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if (user == null) throw new UsernameNotFoundException("Cannot find user with this name");
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(userName, user.getUserPassHash(), authorities);
    }
}
