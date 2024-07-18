package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DatabaseConnection.getConnection();
            PatientManager patientManager = new PatientManager(connection);
            DoctorManager doctorManager = new DoctorManager(connection);
            AppointmentManager appointmentManager = new AppointmentManager(connection);
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nHealthcare Management System");
                System.out.println("1. Register a new patient");
                System.out.println("2. View patient details");
                System.out.println("3. Update patient information");
                System.out.println("4. Delete a patient");
                System.out.println("5. Add a new doctor");
                System.out.println("6. View doctor details");
                System.out.println("7. Update doctor information");
                System.out.println("8. Delete a doctor");
                System.out.println("9. Schedule a new appointment");
                System.out.println("10. View appointment details");
                System.out.println("11. Update appointment information");
                System.out.println("12. Cancel an appointment");
                System.out.println("13. Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patientManager.registerPatient();
                        break;
                    case 2:
                        System.out.print("Enter patient ID: ");
                        int patientId = scanner.nextInt();
                        patientManager.viewPatientDetails(patientId);
                        break;
                    case 3:
                        System.out.print("Enter patient ID: ");
                        patientId = scanner.nextInt();
                        patientManager.updatePatient(patientId);
                        break;
                    case 4:
                        System.out.print("Enter patient ID: ");
                        patientId = scanner.nextInt();
                        patientManager.deletePatient(patientId);
                        break;
                    case 5:
                        doctorManager.addDoctor();
                        break;
                    case 6:
                        System.out.print("Enter doctor ID: ");
                        int doctorId = scanner.nextInt();
                        doctorManager.viewDoctorDetails(doctorId);
                        break;
                    case 7:
                        System.out.print("Enter doctor ID: ");
                        doctorId = scanner.nextInt();
                        doctorManager.updateDoctor(doctorId);
                        break;
                    case 8:
                        System.out.print("Enter doctor ID: ");
                        doctorId = scanner.nextInt();
                        doctorManager.deleteDoctor(doctorId);
                        break;
                    case 9:
                        appointmentManager.scheduleAppointment();
                        break;
                    case 10:
                        System.out.print("Enter appointment ID: ");
                        int appointmentId = scanner.nextInt();
                        appointmentManager.viewAppointmentDetails(appointmentId);
                        break;
                    case 11:
                        System.out.print("Enter appointment ID: ");
                        appointmentId = scanner.nextInt();
                        appointmentManager.updateAppointment(appointmentId);
                        break;
                    case 12:
                        System.out.print("Enter appointment ID: ");
                        appointmentId = scanner.nextInt();
                        appointmentManager.cancelAppointment(appointmentId);
                        break;
                    case 13:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 13);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
