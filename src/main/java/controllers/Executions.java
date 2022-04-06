package controllers;

import entities.Admin;
import entities.Doctor;
import entities.Patient;
import repositories.impls.AdminRepositoryImpl;
import repositories.impls.DoctorRepositoryImpl;
import repositories.impls.PatientRepositoryImpl;
import services.impls.AdminServiceImpl;
import services.impls.DoctorServiceImpl;
import services.impls.PatientServiceImpl;
import services.interfaces.AdminService;
import services.interfaces.DoctorService;
import services.interfaces.PatientService;

import javax.persistence.EntityManagerFactory;
import java.util.Locale;
import java.util.Scanner;

public class Executions {
    private static final EntityManagerFactory factory = EntityManagerSingleton.getInstance();
    private static final AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory, Admin.class));
    private static final DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory,Doctor.class));
    private static final PatientService patientService = new PatientServiceImpl(new PatientRepositoryImpl(factory, Patient.class));
    private static final Scanner sc = new Scanner(System.in);
    private static final Utilities utils = new Utilities();

    public static void main(String[] args) {
        addFirstAdmin();
        label:
        while (true) {
            System.out.println("Press L/l to Login or S/s to Signup or X/x to exit: ");
            String lOrS = sc.nextLine().toUpperCase(Locale.ROOT);
            switch (lOrS) {
                case "L":
                    login();
                    break;
                case "S":
                    signUp();
                    break;
                case "X":
                    break label;
                default:
                    System.out.println("Wrong option.");
                    break;
            }
        }
    }
    public static void login() {
        System.out.println("Username:");
        String username = sc.nextLine();
        System.out.println("Password");
        String password = sc.nextLine();
        auth(username,password);
    }

    private static void auth(String username,String password) {
        Admin probableAdmin = adminService.findByUsername(username);
        Doctor probableDoctor = doctorService.findByUsername(username);
        Patient probablePatient = patientService.findByUsername(username);
        if (probableAdmin != null && probableAdmin.getPassword().equals(password)) guideAdmin(probableAdmin.getId());
        else if (probableDoctor != null && probableDoctor.getPassword().equals(password)) guideDoctor(probableDoctor.getId());
        else if (probablePatient != null && probablePatient.getPassword().equals(password)) guidePatient(probablePatient.getId());
        else System.out.println("Wrong username/password");
    }

    private static void guideAdmin(Integer adminId) {
        /*AdminController controller = new AdminController(factory,adminId);
        controller.entry();*/
    }

    private static void guideDoctor(Integer doctorId) {
        /*CustomerController controller = new CustomerController(factory,customerId);
        controller.entry();*/
    }

    private static void guidePatient(Integer patientId) {

    }

    public static void signUp() {
        System.out.println("Username: ");
        String username = usernameReceiver();
        String password = utils.passwordReceiver();
        Patient patient = new Patient();
        patient.setUsername(username); patient.setPassword(password);
        Patient toRegister = patientService.insert(patient);
        if (toRegister != null) System.out.println("Account registered successfully");
        else System.out.println("Something went wrong with the database");
    }

    private static String usernameReceiver() {
        while (true) {
            String username = sc.nextLine();
            if (patientService.findByUsername(username) == null
                    && doctorService.findByUsername(username) == null
                    && adminService.findByUsername(username) == null) return username;
            else System.out.println("Username already exists");
        }
    }

    private static void addFirstAdmin() {
        if (adminService.findAll().size() == 0) {
            adminService.insert(new Admin("admin","admin"));
            System.out.println("User: admin Pass: admin added");
        }
    }
}
