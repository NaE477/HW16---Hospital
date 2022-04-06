package services.impls;

import entities.Prescription;
import repositories.interfaces.PrescriptionRepository;
import services.interfaces.PrescriptionService;

public class PrescriptionServiceImpl extends BaseServiceImpl<Prescription, PrescriptionRepository> implements PrescriptionService {
    public PrescriptionServiceImpl(PrescriptionRepository repository) {
        super(repository);
    }
}
