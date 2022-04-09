package services.interfaces;

import entities.Clinic;

public interface ClinicService extends BaseService<Clinic> {
    Clinic findByName(String clinicName);
}
