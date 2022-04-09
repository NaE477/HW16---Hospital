package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Doctor extends User {
    public Doctor(String username,String password,Clinic clinic) {
        super(username,password);
        this.clinic = clinic;
    }

    @ManyToOne
    private Clinic clinic;

    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
}
