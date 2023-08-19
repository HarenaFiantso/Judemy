package judemy.fiantso.models;

import lombok.Data;

@Data
public class Users {
    private Long userId;
    private String name;
    private String email;
    private String password;
}
