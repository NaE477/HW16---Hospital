package repositories.interfaces;

import entities.Clinic;
import entities.Doctor;

import java.util.List;

public interface DoctorRepository extends BaseRepository<Doctor> {
    Doctor readByUsername(String username);
    List<Doctor> readAllByClinic(Clinic clinic);
}
