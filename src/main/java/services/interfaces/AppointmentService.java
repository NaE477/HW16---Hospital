package services.interfaces;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;

import java.util.List;

public interface AppointmentService extends BaseService<Appointment> {
    List<Appointment> findAllByDoctor(Doctor doctor);
    List<Appointment> findAllByPatient(Patient patient);
}
