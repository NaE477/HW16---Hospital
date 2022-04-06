package services.impls;

import entities.Clinic;
import repositories.interfaces.ClinicRepository;
import services.interfaces.ClinicService;

public class ClinicServiceImpl extends BaseServiceImpl<Clinic, ClinicRepository> implements ClinicService {
    public ClinicServiceImpl(ClinicRepository repository) {
        super(repository);
    }
}
