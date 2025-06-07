package Controlador;

import Modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorRegistroCita {

    public static String buscarPacienteEnBD(String dni) {
        String nombreCompleto = null;

        String sql = "SELECT nombre, apellidos FROM pacientes WHERE DNI = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellidos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCompleto;
    }

    public boolean registrarCitaEnBD(String dni, String paciente, String especialidad,
            String medico, String fecha, String hora, String observaciones) {
        String sql = "INSERT INTO citas (DNI_Paciente, Paciente, Especialidad, Medico, Fecha, Hora, Observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            pstmt.setString(2, paciente);
            pstmt.setString(3, especialidad);
            pstmt.setString(4, medico);
            pstmt.setString(5, fecha);
            pstmt.setString(6, hora);
            pstmt.setString(7, observaciones);

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;  // Retorna true si se insertó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
