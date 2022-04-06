package services.impls;

import entities.Appointment;
import repositories.interfaces.AppointmentRepository;
import services.interfaces.AppointmentService;

public class AppointmentServiceImpl extends BaseServiceImpl<Appointment,AppointmentRepository> implements AppointmentService {
    public AppointmentServiceImpl(AppointmentRepository repository) {
        super(repository);
    }
}
