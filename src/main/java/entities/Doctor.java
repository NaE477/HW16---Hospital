package entities;

import entities.base.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Doctor extends User {
    public Doctor(String username,String password,Clinic clinic) {
        super(username,password);
        this.clinic = clinic;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Clinic clinic;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Appointment> appointments;

    @Override
    public String toString() {
        return "Doctor{" +
                "ID=" + super.getId() +
                ", name=" + super.getUsername() +
                ", clinic=" + clinic.getClinicName() +
                '}';
    }
}
