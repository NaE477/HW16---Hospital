package repositories.impls;

import entities.Clinic;
import repositories.interfaces.ClinicRepository;

import javax.persistence.EntityManagerFactory;

public class ClinicRepositoryImpl extends BaseRepositoryImpl<Clinic> implements ClinicRepository {
    public ClinicRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Clinic> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public Clinic readByName(String clinicName) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select c from Clinic c where c.clinicName = :clinicName", getClazz())
                    .setParameter("clinicName",clinicName)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
