package services.impls;

import entities.Clinic;
import entities.Doctor;
import repositories.interfaces.DoctorRepository;
import services.interfaces.DoctorService;

import java.util.List;

public class DoctorServiceImpl extends BaseServiceImpl<Doctor,DoctorRepository> implements DoctorService {
    public DoctorServiceImpl(DoctorRepository repository) {
        super(repository);
    }

    @Override
    public Doctor findByUsername(String username) {
        return repository.readByUsername(username);
    }

    @Override
    public List<Doctor> findAllByClinic(Clinic clinic) {
        return repository.readAllByClinic(clinic);
    }
}
