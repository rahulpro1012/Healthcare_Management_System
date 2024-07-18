package main;

import java.sql.*;
import java.util.Scanner;

public class PatientManager {
    private Connection connection;

    public PatientManager(Connection connection) {
        this.connection = connection;
    }

    public void registerPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String query = "INSERT INTO Patient (name, date_of_birth, gender, address) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(dob));
            stmt.setString(3, gender);
            stmt.setString(4, address);
            stmt.executeUpdate();
            System.out.println("Patient registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatientDetails(int patientId) {
        String query = "SELECT * FROM Patient WHERE patient_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Date of Birth: " + rs.getDate("date_of_birth"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Address: " + rs.getString("address"));
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(int patientId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        String query = "UPDATE Patient SET name = ?, date_of_birth = ?, gender = ?, address = ? WHERE patient_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(dob));
            stmt.setString(3, gender);
            stmt.setString(4, address);
            stmt.setInt(5, patientId);
            stmt.executeUpdate();
            System.out.println("Patient updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String query = "DELETE FROM Patient WHERE patient_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

