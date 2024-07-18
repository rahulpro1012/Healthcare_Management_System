package main;

import java.sql.*;
import java.util.Scanner;

public class DoctorManager {
    private Connection connection;

    public DoctorManager(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String query = "INSERT INTO Doctor (name, specialization, contact_number, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setString(3, contactNumber);
            stmt.setString(4, email);
            stmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctorDetails(int doctorId) {
        String query = "SELECT * FROM Doctor WHERE doctor_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Specialization: " + rs.getString("specialization"));
                System.out.println("Contact Number: " + rs.getString("contact_number"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(int doctorId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter new contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        String query = "UPDATE Doctor SET name = ?, specialization = ?, contact_number = ?, email = ? WHERE doctor_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setString(3, contactNumber);
            stmt.setString(4, email);
            stmt.setInt(5, doctorId);
            stmt.executeUpdate();
            System.out.println("Doctor updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {
        String query = "DELETE FROM Doctor WHERE doctor_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

