package io.programminglife.personalfinancesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
