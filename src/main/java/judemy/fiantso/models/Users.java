package judemy.fiantso.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Users {
    private int user_id;
    private String name;
    private String email;
    private String password;

}
