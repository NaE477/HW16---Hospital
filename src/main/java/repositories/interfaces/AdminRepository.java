package repositories.interfaces;

import entities.Admin;

public interface AdminRepository extends BaseRepository<Admin> {
    Admin readByUsername(String username);
}
