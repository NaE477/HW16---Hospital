package repositories.impls;

import entities.Admin;
import repositories.interfaces.AdminRepository;

import javax.persistence.EntityManagerFactory;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin> implements AdminRepository {
    public AdminRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Admin> clazz) {
        super(entityManagerFactory, clazz);
    }
}
