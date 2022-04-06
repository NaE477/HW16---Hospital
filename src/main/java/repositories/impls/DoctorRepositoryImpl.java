package repositories.impls;

import entities.Doctor;
import repositories.interfaces.DoctorRepository;

import javax.persistence.EntityManagerFactory;

public class DoctorRepositoryImpl extends BaseRepositoryImpl<Doctor> implements DoctorRepository {
    public DoctorRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Doctor> clazz) {
        super(entityManagerFactory, clazz);
    }
}
