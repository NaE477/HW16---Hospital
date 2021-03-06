package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
}
