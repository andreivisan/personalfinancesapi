package io.programminglife.personalfinancesapi.util.autentication;

import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.payload.SignupRequest;

public class AuthenticationUtil {

    public static Client signupRequestToClient(SignupRequest signupRequest) {
        Client client = new Client();

        client.setName(signupRequest.getName());
        client.setUsername(signupRequest.getUsername());
        client.setEmail(signupRequest.getEmail());

        return client;
    }

}
