package repositories.interfaces;

import entities.Patient;

public interface PatientRepository extends BaseRepository<Patient> {
    Patient readByUsername(String username);
}
