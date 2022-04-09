package controllers;

import entities.Patient;
import repositories.impls.PatientRepositoryImpl;
import services.impls.PatientServiceImpl;
import services.interfaces.PatientService;

import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class PatientController {
    private final EntityManagerFactory factory;
    private final Utilities utils;
    private final Scanner sc;
    private final Patient patient;

    public PatientController(EntityManagerFactory factory,Integer patientId) {
        this.factory = factory;
        utils = new Utilities();
        sc = new Scanner(System.in);

        PatientService patientService = new PatientServiceImpl(new PatientRepositoryImpl(factory,Patient.class));
        patient = patientService.findById(patientId);
    }

    public void entry() {
        while (true) {

        }
    }
}
