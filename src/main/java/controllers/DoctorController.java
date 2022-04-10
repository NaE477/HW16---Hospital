package controllers;

import entities.Appointment;
import entities.Doctor;
import repositories.impls.AppointmentRepositoryImpl;
import repositories.impls.DoctorRepositoryImpl;
import services.impls.AppointmentServiceImpl;
import services.impls.DoctorServiceImpl;
import services.interfaces.AppointmentService;
import services.interfaces.DoctorService;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DoctorController {
    private final EntityManagerFactory factory;
    private final Scanner sc;
    private final Utilities utils;
    private final Doctor doctor;

    public DoctorController(EntityManagerFactory factory,Integer doctorId) {
        this.factory = factory;
        sc = new Scanner(System.in);
        utils = new Utilities();
        DoctorService doctorService = new DoctorServiceImpl(new DoctorRepositoryImpl(factory,Doctor.class));
        doctor = doctorService.findById(doctorId);
    }

    public void entry() {
        label:
        while (true) {
            System.out.println("Welcome " + doctor.getUsername());
            System.out.println("1-Add Appointment Time");
            System.out.println("2-View Profile");
            System.out.println("3-Write Prescription");
            System.out.println("0-Exit");
            System.out.print("Option: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    addAppointmentTime();
                    break;
                case "2":
                    System.out.println(doctor);
                    break;
                case "3":
                    writePrescription();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private void addAppointmentTime() {
        AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl(factory,Appointment.class));
        LocalDateTime time = utils.futureTimeReceiver();
        Appointment appointment = new Appointment(doctor,null,time,false,"");
        if (appointmentService.insert(appointment) != null) System.out.println("Appointment Saved");
        else System.out.println("Something went wrong with the controller");
    }

    private void writePrescription() {
        AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl(factory,Appointment.class));
        List<Appointment> unwrittenPrescribeAppointments = appointmentService.findAllByDoctor(doctor).stream().filter(a -> Objects.equals(a.getPrescription(), "") && a.getIsBooked()).collect(Collectors.toList());
        unwrittenPrescribeAppointments.forEach(a -> System.out.println("ID=" + a.getId() + ", Patient= " + a.getPatient().getUsername() + ", Time= " + a.getAppointmentTime()));
        System.out.print("Enter appointment ID: ");
        Integer appointmentId = utils.intReceiver();
        Appointment appointment = unwrittenPrescribeAppointments.stream().filter(a -> Objects.equals(a.getId(), appointmentId)).findAny().orElse(null);
        if (appointment == null) System.out.println("Wrong appointment ID");
        else {
            System.out.println("Enter Prescription:");
            String prescription = sc.nextLine();
            appointment.setPrescription(prescription);
            var toUpdate = appointmentService.update(appointment);
            if (toUpdate != null) System.out.println("Prescription saved");
            else System.out.println("Something went wrong with the database");
        }
    }

    private String prescriptionReceiver() {
        while (true) {
            String prescription = sc.nextLine();
            if (!prescription.equals("")) return prescription;
            else System.out.println("Write something!");
        }
    }
}
