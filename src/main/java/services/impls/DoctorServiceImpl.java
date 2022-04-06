package services.impls;

import entities.Doctor;
import repositories.interfaces.BaseUserRepository;
import repositories.interfaces.DoctorRepository;
import services.interfaces.DoctorService;

public class DoctorServiceImpl extends BaseUserServiceImpl<Doctor> implements DoctorService {
    public DoctorServiceImpl(BaseUserRepository<Doctor> repository) {
        super(repository);
    }
}
