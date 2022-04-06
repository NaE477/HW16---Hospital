package entities;

import entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Clinic extends BaseEntity {
    @OneToMany(mappedBy = "clinic",fetch = FetchType.EAGER)
    private Set<Doctor> doctors;
}
