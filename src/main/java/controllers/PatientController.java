package controllers;

import entities.Appointment;
import entities.Clinic;
import entities.Doctor;
import entities.Patient;
import repositories.impls.AppointmentRepositoryImpl;
import repositories.impls.ClinicRepositoryImpl;
import repositories.impls.DoctorRepositoryImpl;
import repositories.impls.PatientRepositoryImpl;
import services.impls.AppointmentServiceImpl;
import services.impls.ClinicServiceImpl;
import services.impls.DoctorServiceImpl;
import services.impls.PatientServiceImpl;
import services.interfaces.AppointmentService;
import services.interfaces.ClinicService;
import services.interfaces.DoctorService;
import services.interfaces.PatientService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        label:
        while (true) {
            System.out.println("Welcome " + patient.getUsername());
            System.out.println("1-Reserve Appointment");
            System.out.println("2-View Prescriptions");
            System.out.println("0-Exit");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    reserveAppointment();
                    break;
                case "2":
                    viewPrescriptions();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private void reserveAppointment() {
        AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl(factory, Appointment.class));
        Clinic clinic = clinicReceiver();
        if (clinic == null) System.out.println("Wrong Clinic ID");
        else {
            Doctor doctor = doctorReceiver(clinic);
            if (doctor == null) System.out.println("Wrong Doctor ID");
            else {
                Appointment appointment = appointmentReceiver(doctor);
                if (appointment == null) System.out.println("Wrong Appointment ID");
                else {
                    appointment.setPatient(patient);
                    appointment.setIsBooked(true);
                    var toSave = appointmentService.update(appointment);
                    if (toSave != null) System.out.println("Appointment reserved");
                    else System.out.println("Something went wrong with the database");
                }
            }
        }
    }

    private Clinic clinicReceiver() {
        ClinicService clinicService = new ClinicServiceImpl(new ClinicRepositoryImpl(factory,Clinic.class));
        List<Clinic> clinics = clinicService.findAll();
        utils.iterateThrough(clinics);
        System.out.print("Enter clinic ID: ");
        Integer clinicId = utils.intReceiver();
        return clinicService.findById(clinicId);
    }

    private Doctor doctorReceiver(Clinic clinic) {
        DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory,Doctor.class));
        List<Doctor> doctors = doctorService.findAllByClinic(clinic);
        utils.iterateThrough(doctors);
        System.out.print("Enter doctor ID: ");
        Integer doctorId = utils.intReceiver();
        return doctorService.findById(doctorId);
    }

    private Appointment appointmentReceiver(Doctor doctor) {
        AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl(factory,Appointment.class));
        List<Appointment> appointments = appointmentService.findAllByDoctor(doctor);
        List<Appointment> freeAppointments = appointments.stream().filter(a -> !a.getIsBooked()).collect(Collectors.toList());
        freeAppointments.forEach(a -> System.out.println("ID: " + a.getId() + ", Doctor: " + a.getDoctor() + ", Time: " + a.getAppointmentTime()));
        System.out.println("Enter appointment ID: ");
        Integer appointmentId = utils.intReceiver();
        return appointmentService.findById(appointmentId);
    }

    private void viewPrescriptions() {
        AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl(factory,Appointment.class));
        appointmentService
                .findAllByPatient(patient)
                .stream()
                .filter(a -> !Objects.equals(a.getPrescription(), ""))
                .forEach(a -> System.out.println("Doctor: " + a.getDoctor().getUsername() + ", Prescription: " + a.getPrescription()));
    }
}
