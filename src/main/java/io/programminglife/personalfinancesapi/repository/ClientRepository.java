package io.programminglife.personalfinancesapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByUsernameOrEmail(String username, String email);

}
