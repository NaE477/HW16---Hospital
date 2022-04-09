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
import java.util.Scanner;

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
        Integer day = utils.dayReceiver();
        int hour = utils.hourReceiver();
        int minute = utils.minuteReceiver();
        LocalDateTime time = LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),day,hour,minute);
        Appointment appointment = new Appointment(doctor,null,time,false,null);
        if (appointmentService.insert(appointment) != null) System.out.println("Appointment Saved");
        else System.out.println("Something went wrong with the controller");
    }


}
