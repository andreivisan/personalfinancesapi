package io.programminglife.personalfinancesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
