package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Patient extends User {
    @OneToMany(mappedBy = "patient")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
}
