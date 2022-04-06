package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Doctor extends User {
    @ManyToOne
    private Clinic clinic;

    @OneToOne(mappedBy = "doctor")
    private Set<Prescription> prescriptions;

    private Set<Duration> freeTimes;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
}
