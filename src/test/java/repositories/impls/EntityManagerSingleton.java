package repositories.impls;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private EntityManagerSingleton() {
    }

    private static class Holder {
        static EntityManagerFactory INSTANCE;

        static {
            INSTANCE = Persistence.createEntityManagerFactory("hospital");
        }
    }

    public static EntityManagerFactory getInstance() {
        return Holder.INSTANCE;
    }
}
