package repositories.impls;

import entities.Clinic;
import entities.Doctor;
import repositories.interfaces.DoctorRepository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DoctorRepositoryImpl extends BaseRepositoryImpl<Doctor> implements DoctorRepository {
    public DoctorRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Doctor> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public Doctor readByUsername(String username) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select d from Doctor d where d.username = :username", super.getClazz())
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Doctor> readAllByClinic(Clinic clinic) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select d from Doctor d where d.clinic.id = :clinicId", Doctor.class)
                    .setParameter("clinicId", clinic.getId())
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
