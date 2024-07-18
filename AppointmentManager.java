package main;

import java.sql.*;
import java.util.Scanner;

public class AppointmentManager {
    private Connection connection;

    public AppointmentManager(Connection connection) {
        this.connection = connection;
    }

    public void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter appointment time (HH:MM:SS): ");
        String time = scanner.nextLine();

        String query = "INSERT INTO Appointment (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, 'scheduled')";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(time));
            stmt.executeUpdate();
            System.out.println("Appointment scheduled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAppointmentDetails(int appointmentId) {
        String query = "SELECT * FROM Appointment WHERE appointment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("Appointment Date: " + rs.getDate("appointment_date"));
                System.out.println("Appointment Time: " + rs.getTime("appointment_time"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAppointment(int appointmentId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter new appointment time (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Enter new status (scheduled/cancelled): ");
        String status = scanner.nextLine();

        String query = "UPDATE Appointment SET appointment_date = ?, appointment_time = ?, status = ? WHERE appointment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(date));
            stmt.setTime(2, Time.valueOf(time));
            stmt.setString(3, status);
            stmt.setInt(4, appointmentId);
            stmt.executeUpdate();
            System.out.println("Appointment updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelAppointment(int appointmentId) {
        String query = "UPDATE Appointment SET status = 'cancelled' WHERE appointment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
            System.out.println("Appointment cancelled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

