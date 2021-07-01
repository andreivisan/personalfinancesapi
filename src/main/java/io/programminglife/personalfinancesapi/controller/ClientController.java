package io.programminglife.personalfinancesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.payload.ClientSummary;
import io.programminglife.personalfinancesapi.security.CurrentUser;
import io.programminglife.personalfinancesapi.security.UserPrincipal;
import io.programminglife.personalfinancesapi.service.ClientService;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/me")
    public ResponseEntity<ClientSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        ClientSummary clientSummary = new ClientSummary(currentUser.getId(), currentUser.getUsername(),
                currentUser.getName());

        return ResponseEntity.ok().body(clientSummary);
    }

    @PostMapping("/save")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(clientService.findClientById(id));
        } catch (MyFinancesException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        clientService.deleteClient(id);
    }

}
