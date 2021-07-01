package io.programminglife.personalfinancesapi.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    private String name;

    private String username;

    private String email;

    private String password;

}
