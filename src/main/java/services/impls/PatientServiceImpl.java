package services.impls;

import entities.Patient;
import repositories.interfaces.BaseUserRepository;
import repositories.interfaces.PatientRepository;
import services.interfaces.PatientService;

public class PatientServiceImpl extends BaseUserServiceImpl<Patient> implements PatientService {
    public PatientServiceImpl(BaseUserRepository<Patient> repository) {
        super(repository);
    }
}
