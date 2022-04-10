package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Patient extends User {

    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Appointment> appointments;
}
