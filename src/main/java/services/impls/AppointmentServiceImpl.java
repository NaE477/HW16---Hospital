package services.impls;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import repositories.interfaces.AppointmentRepository;
import services.interfaces.AppointmentService;

import java.util.List;

public class AppointmentServiceImpl extends BaseServiceImpl<Appointment,AppointmentRepository> implements AppointmentService {
    public AppointmentServiceImpl(AppointmentRepository repository) {
        super(repository);
    }

    @Override
    public List<Appointment> findAllByDoctor(Doctor doctor) {
        return repository.readAllByDoctor(doctor);
    }

    @Override
    public List<Appointment> findAllByPatient(Patient patient) {
        return repository.readAllByPatient(patient);
    }
}
