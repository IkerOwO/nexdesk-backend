package com.iker.nexdesk.services;

import com.iker.nexdesk.dtos.LoginRequest;
import com.iker.nexdesk.dtos.LoginResponse;
import com.iker.nexdesk.dtos.RegisterUserRequest;
import com.iker.nexdesk.entities.User;
import com.iker.nexdesk.exceptions.PasswordsDontMatchException;
import com.iker.nexdesk.exceptions.UserAlreadyExistsException;
import com.iker.nexdesk.exceptions.UserDontExistsException;
import com.iker.nexdesk.repositories.AuthRepository;
import com.iker.nexdesk.security.JwtService;
import com.iker.nexdesk.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author IkerOwO
 * <p>
 *     Auth Service Class
 * </p>
 */
@Service
public class AuthService implements UserDetailsService {
    private final AuthRepository repository;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public AuthService(AuthRepository repository, JwtService jwtService, PasswordEncoder encoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public void registerUser(RegisterUserRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email associated with another account!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setAccount_created_at(request.getAccount_created_at());
        user.setRole("ROLE_USER");
        repository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserDontExistsException("The user doesn't exists!"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordsDontMatchException("Incorrect login!");
        }

        return new LoginResponse(
                jwtService.generateToken(user),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
