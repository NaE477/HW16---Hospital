package repositories.interfaces;

import entities.Clinic;

public interface ClinicRepository extends BaseRepository<Clinic>{
    Clinic readByName(String clinicName);
}
