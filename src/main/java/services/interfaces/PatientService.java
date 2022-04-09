package services.interfaces;

import entities.Patient;

public interface PatientService extends BaseService<Patient> {
    Patient findByUsername(String username);
}
