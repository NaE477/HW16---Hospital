package services.impls;

import entities.Doctor;
import repositories.interfaces.DoctorRepository;
import services.interfaces.DoctorService;

public class DoctorServiceImpl extends BaseServiceImpl<Doctor, DoctorRepository> implements DoctorService {
    public DoctorServiceImpl(DoctorRepository repository) {
        super(repository);
    }
}
