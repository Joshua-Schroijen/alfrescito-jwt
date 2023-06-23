package be.joshuaschroijen.alfrescito.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.joshuaschroijen.alfrescito.model.AlfrescitoRole;
import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;
import be.joshuaschroijen.alfrescito.dto.UserRegistrationRequest;
import be.joshuaschroijen.alfrescito.repository.AlfrescitoUserRepository;
import be.joshuaschroijen.alfrescito.repository.AlfrescitoRoleRepository;

@Service
public class AlfrescitoUserDetailsServiceImpl implements AlfrescitoUserDetailsService {

    private AlfrescitoUserRepository userRepository;
    private AlfrescitoRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AlfrescitoUserDetailsServiceImpl(AlfrescitoUserRepository userRepository,
            AlfrescitoRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AlfrescitoUser user = userRepository.findOneByEmail(email);

        if (user != null) {
            return new User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @Override
    public void saveUser(UserRegistrationRequest userDto) {
        AlfrescitoUser user = new AlfrescitoUser();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        AlfrescitoRole role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public AlfrescitoUser findUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<AlfrescitoRole> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    private AlfrescitoRole checkRoleExist() {
        AlfrescitoRole role = new AlfrescitoRole();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}