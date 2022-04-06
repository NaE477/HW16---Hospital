package repositories.impls;

import entities.Patient;
import repositories.interfaces.PatientRepository;

import javax.persistence.EntityManagerFactory;

public class PatientRepositoryImpl extends BaseUserRepositoryImpl<Patient> implements PatientRepository {
    public PatientRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Patient> clazz) {
        super(entityManagerFactory, clazz);
    }
}
