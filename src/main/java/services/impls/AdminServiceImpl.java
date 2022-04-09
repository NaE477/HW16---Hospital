package services.impls;

import entities.Admin;
import repositories.interfaces.AdminRepository;
import services.interfaces.AdminService;

public class AdminServiceImpl extends BaseServiceImpl<Admin,AdminRepository> implements AdminService {
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public Admin findByUsername(String username) {
        return repository.readByUsername(username);
    }
}
