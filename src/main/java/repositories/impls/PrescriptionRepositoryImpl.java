package repositories.impls;

import entities.Prescription;
import repositories.interfaces.PrescriptionRepository;

import javax.persistence.EntityManagerFactory;

public class PrescriptionRepositoryImpl extends BaseRepositoryImpl<Prescription> implements PrescriptionRepository {
    public PrescriptionRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Prescription> clazz) {
        super(entityManagerFactory, clazz);
    }
}
