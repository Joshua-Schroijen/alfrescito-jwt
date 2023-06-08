package be.joshuaschroijen.alfrescito.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;
import be.joshuaschroijen.alfrescito.dto.UserRegistrationRequest;

public interface AlfrescitoUserDetailsService extends UserDetailsService {
    public void saveUser(UserRegistrationRequest userDto);

    public AlfrescitoUser findUserByEmail(String email);
}
