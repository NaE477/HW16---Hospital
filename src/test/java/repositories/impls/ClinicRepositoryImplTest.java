package repositories.impls;

import entities.Clinic;
import org.junit.jupiter.api.Test;
import repositories.interfaces.ClinicRepository;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class ClinicRepositoryImplTest {

    @Test
    void readByName() {
        //Arrange
        ClinicRepository clinicRepository = new ClinicRepositoryImpl(EntityManagerSingleton.getInstance(),Clinic.class);
        Clinic clinic = new Clinic(); clinic.setClinicName("Clinic");
        clinicRepository.ins(clinic);
        //Act
        Clinic toFind = clinicRepository.readByName("Clinic");
        clinicRepository.truncate("clinic");
        //Assert
        assertNotNull(toFind);
    }
}