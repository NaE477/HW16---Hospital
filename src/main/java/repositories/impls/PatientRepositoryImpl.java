package repositories.impls;

import entities.Patient;
import repositories.interfaces.PatientRepository;

import javax.persistence.EntityManagerFactory;

public class PatientRepositoryImpl extends BaseRepositoryImpl<Patient> implements PatientRepository {
    public PatientRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Patient> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public Patient readByUsername(String username) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select p from Patient p where p.username = :username", super.getClazz())
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
