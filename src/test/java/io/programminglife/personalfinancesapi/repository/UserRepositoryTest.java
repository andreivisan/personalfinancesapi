package io.programminglife.personalfinancesapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.programminglife.personalfinancesapi.entity.Client;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void test_no_client_if_repository_is_empty() {
        List<Client> clients = clientRepository.findAll();

        assertEquals(0, clients.size());
    }

    @Test
    public void test_save_client() {
        Client testClient = clientRepository.save(new Client("test@test.com", "1234"));

        assertNotNull(testClient);
        assertEquals("1234", testClient.getIban());
    }

    @Test
    public void test_get_client_by_id() {
        Client testClient = clientRepository.save(new Client("test@test.com", "1234"));

        Client client = clientRepository.findById(testClient.getId()).orElse(null);

        assertNotNull(client);
        assertEquals(testClient.getIban(), client.getIban());
    }

    @Test
    public void test_get_all_clients() {
        clientRepository.save(new Client("test@test.com", "1234"));
        clientRepository.save(new Client("test1@test.com", "1235"));

        List<Client> clients = clientRepository.findAll();

        assertNotNull(clients);
        assertEquals(2, clients.size());
    }

    @Test
    public void test_delete_client_by_id() {
        Client testClient = clientRepository.save(new Client("test@test.com", "1234"));

        clientRepository.deleteById(testClient.getId());

        List<Client> clients = clientRepository.findAll();

        assertEquals(0, clients.size());
    }

}
