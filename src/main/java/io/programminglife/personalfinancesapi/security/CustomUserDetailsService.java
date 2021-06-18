package io.programminglife.personalfinancesapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.ClientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new MyFinancesException(
                        String.format("Client not found for username or email %s", usernameOrEmail)));

        return UserPrincipal.create(client);
    }

    public UserDetails loadUserById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new MyFinancesException(String.format("Client not found for id %s", id)));

        return UserPrincipal.create(client);
    }

}
