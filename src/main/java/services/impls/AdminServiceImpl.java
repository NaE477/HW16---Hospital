package services.impls;

import entities.Admin;
import repositories.interfaces.BaseUserRepository;
import services.interfaces.AdminService;

public class AdminServiceImpl extends BaseUserServiceImpl<Admin> implements AdminService {
    public AdminServiceImpl(BaseUserRepository<Admin> repository) {
        super(repository);
    }
}
