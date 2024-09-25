package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Próba logowania dla: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika o adresie e-mail: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapAuthorities(user.getUserAuthority())
        );
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Set<UserAuthority> userAuthority) {
        return userAuthority.stream()
                .map(ua -> new SimpleGrantedAuthority(ua.getAuthority().getName()))
                .toList();
    }
}
