package repositories.impls;

import entities.Admin;
import repositories.interfaces.AdminRepository;

import javax.persistence.EntityManagerFactory;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin> implements AdminRepository {
    public AdminRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Admin> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public Admin readByUsername(String username) {
        try {
            return super
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery("select a from Admin a where a.username = :username", super.getClazz())
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
