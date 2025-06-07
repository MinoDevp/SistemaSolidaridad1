package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    public boolean registrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nombre, apellidos, dni, fecha_nacimiento, telefono, direccion, grupo_sanguineo, alergias, antecedentes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellidos());
            stmt.setString(3, paciente.getDni());
            stmt.setString(4, paciente.getFechaNacimiento());
            stmt.setString(5, paciente.getTelefono());
            stmt.setString(6, paciente.getDireccion());
            stmt.setString(7, paciente.getGrupoSanguineo());
            stmt.setString(8, paciente.getAlergias());
            stmt.setString(9, paciente.getAntecedentes());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
