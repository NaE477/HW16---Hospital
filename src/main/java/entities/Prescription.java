package entities;

import entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Prescription extends BaseEntity {
    @Column(nullable = false)
    private String prescription;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
