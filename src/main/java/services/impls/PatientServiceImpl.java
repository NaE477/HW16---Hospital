package services.impls;

import entities.Patient;
import repositories.interfaces.PatientRepository;
import services.interfaces.PatientService;

public class PatientServiceImpl extends BaseServiceImpl<Patient,PatientRepository> implements PatientService {
    public PatientServiceImpl(PatientRepository repository) {
        super(repository);
    }

    @Override
    public Patient findByUsername(String username) {
        return repository.readByUsername(username);
    }
}
