package io.programminglife.personalfinancesapi.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientSummary {

    private Long id;

    private String username;

    private String name;

    public ClientSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

}
