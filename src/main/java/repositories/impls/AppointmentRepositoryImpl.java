package repositories.impls;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import repositories.interfaces.AppointmentRepository;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryImpl extends BaseRepositoryImpl<Appointment> implements AppointmentRepository {
    public AppointmentRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Appointment> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public List<Appointment> readAllByDoctor(Doctor doctor) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select a from Appointment a where a.doctor.id = :doctorId", Appointment.class)
                    .setParameter("doctorId", doctor.getId())
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Appointment> readAllByPatient(Patient patient) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select a from Appointment a where a.patient.id = :patientId",Appointment.class)
                    .setParameter("patientId",patient.getId())
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
