package judemy.fiantso.entities;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Users {
    private String name;
    private String email;
    private String password;
}
