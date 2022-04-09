package repositories.interfaces;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;

import java.util.List;

public interface AppointmentRepository extends BaseRepository<Appointment> {
    List<Appointment> readAllByDoctor(Doctor doctor);
    List<Appointment> readAllByPatient(Patient patient);
}
