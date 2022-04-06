package repositories.impls;

import entities.Clinic;
import repositories.interfaces.ClinicRepository;

import javax.persistence.EntityManagerFactory;

public class ClinicRepositoryImpl extends BaseRepositoryImpl<Clinic> implements ClinicRepository {
    public ClinicRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Clinic> clazz) {
        super(entityManagerFactory, clazz);
    }
}
