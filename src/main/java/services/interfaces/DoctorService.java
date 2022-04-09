package services.interfaces;

import entities.Clinic;
import entities.Doctor;

import java.util.List;

public interface DoctorService extends BaseService<Doctor> {
    Doctor findByUsername(String username);
    List<Doctor> findAllByClinic(Clinic clinic);
}
