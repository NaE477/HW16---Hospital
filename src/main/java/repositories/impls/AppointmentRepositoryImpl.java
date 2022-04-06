package repositories.impls;

import entities.Appointment;
import repositories.interfaces.AppointmentRepository;

import javax.persistence.EntityManagerFactory;

public class AppointmentRepositoryImpl extends BaseRepositoryImpl<Appointment> implements AppointmentRepository {
    public AppointmentRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Appointment> clazz) {
        super(entityManagerFactory, clazz);
    }
}
