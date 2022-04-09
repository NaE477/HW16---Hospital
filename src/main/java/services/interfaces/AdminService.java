package services.interfaces;

import entities.Admin;

public interface AdminService extends BaseService<Admin> {
    Admin findByUsername(String username);
}
