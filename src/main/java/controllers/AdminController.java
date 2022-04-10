package controllers;

import entities.Admin;
import entities.Clinic;
import entities.Doctor;
import entities.Patient;
import repositories.impls.AdminRepositoryImpl;
import repositories.impls.ClinicRepositoryImpl;
import repositories.impls.DoctorRepositoryImpl;
import repositories.impls.PatientRepositoryImpl;
import services.impls.AdminServiceImpl;
import services.impls.ClinicServiceImpl;
import services.impls.DoctorServiceImpl;
import services.impls.PatientServiceImpl;
import services.interfaces.AdminService;
import services.interfaces.ClinicService;
import services.interfaces.DoctorService;
import services.interfaces.PatientService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final Admin admin;
    private final EntityManagerFactory factory;
    private final Scanner sc;
    private final Utilities utils;

    public AdminController(EntityManagerFactory factory, Integer adminId) {
        this.factory = factory;

        sc = new Scanner(System.in);
        utils = new Utilities();
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory, Admin.class));
        admin = adminService.findById(adminId);
    }

    public void entry() {
        label:
        while (true) {
            System.out.println("Welcome " + admin.getUsername());
            System.out.println("1-Add Admin");
            System.out.println("2-Add Clinic(Add before adding doctor)");
            System.out.println("3-Add Doctor");
            System.out.println("4-Checkout Doctor History");
            System.out.println("5-Checkout Patient History");
            System.out.println("0-Exit");
            System.out.print("Option: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    addAdmin();
                    break;
                case "2":
                    addClinic();
                    break;
                case "3":
                    addDoctor();
                    break;
                case "4":
                    checkDoctor();
                    break;
                case "5":
                    checkPatient();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private void addAdmin() {
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory, Admin.class));

        System.out.println("Username: ");
        String username = usernameReceiver();
        System.out.println("Password: ");
        String password = sc.nextLine();
        Admin toIns = adminService.insert(new Admin(username, password));
        if (toIns != null) System.out.println("Admin created");
        else System.out.println("Something went wrong with the database.");
    }

    private void addClinic() {
        ClinicService clinicService = new ClinicServiceImpl(new ClinicRepositoryImpl(factory, Clinic.class));

        System.out.println("Clinic Name: ");
        String clinicName = clinicNameReceiver();
        Clinic clinic = new Clinic();
        clinic.setClinicName(clinicName);
        Clinic toSave = clinicService.insert(clinic);
        if (toSave != null) System.out.println("Clinic Added");
        else System.out.println("Something went wrong with the database");
    }

    private void addDoctor() {
        DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory, Doctor.class));

        System.out.println("Username: ");
        String username = usernameReceiver();
        System.out.println("Password: ");
        String password = sc.nextLine();
        Clinic clinic = clinicReceiver();
        if (clinic != null) {
            Doctor toIns = doctorService.insert(new Doctor(username, password, clinic));
            if (toIns != null) System.out.println("Doctor created");
            else System.out.println("Something went wrong with the database.");
        } else {
            System.out.println("Choosing clinic led to problem");
        }
    }

    private String usernameReceiver() {
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory, Admin.class));
        PatientService patientService = new PatientServiceImpl(new PatientRepositoryImpl(factory, Patient.class));
        DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory, Doctor.class));
        while (true) {
            String username = sc.nextLine();
            if (adminService.findByUsername(username) == null
                    && patientService.findByUsername(username) == null
                    && doctorService.findByUsername(username) == null)
                return username;
            else System.out.println("Username already exists");
        }
    }

    private String clinicNameReceiver() {
        ClinicService clinicService = new ClinicServiceImpl(new ClinicRepositoryImpl(factory, Clinic.class));

        while (true) {
            String clinicName = sc.nextLine();
            if (clinicService.findByName(clinicName) == null) return clinicName;
            else System.out.println("Clinic Name already Exists");
        }
    }

    private Clinic clinicReceiver() {
        ClinicService clinicService = new ClinicServiceImpl(new ClinicRepositoryImpl(factory, Clinic.class));
        List<Clinic> clinics = clinicService.findAll();
        if (clinics.size() == 0) {
            System.out.println("No Clinic was added yet.");
            return null;
        } else {
            utils.iterateThrough(clinics);
            Integer clinicId = utils.intReceiver();
            return clinicService.findById(clinicId);
        }
    }

    private void checkDoctor() {
        DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory, Doctor.class));
        List<Doctor> doctors = doctorService.findAll();
        utils.iterateThrough(doctors);
        System.out.print("Enter doctor ID: ");
        Integer doctorID = utils.intReceiver();
        Doctor doctor = doctorService.findById(doctorID);
        if (doctor != null) doctor.getAppointments().forEach(System.out::println);
        else System.out.println("Wrong Doctor ID");
    }

    private void checkPatient() {
        PatientService patientService = new PatientServiceImpl(new PatientRepositoryImpl(factory, Patient.class));
        List<Patient> patients = patientService.findAll();
        utils.iterateThrough(patients);
        System.out.print("Enter patient ID: ");
        Integer patientID = utils.intReceiver();
        Patient patient = patientService.findById(patientID);
        if (patient != null) patient.getAppointments().forEach(System.out::println);
        else System.out.println("Wrong Patient ID");
    }
}
