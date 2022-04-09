package entities;

import entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Clinic extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String clinicName;

    @OneToMany(mappedBy = "clinic",fetch = FetchType.EAGER)
    private Set<Doctor> doctors;
}
