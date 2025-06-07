package Modelo;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class Mantenimientocontraseña {

    public static void main(String[] args) {
        modificarContraseñaUsuario("admin123","123");
    }

    // Función para modificar la contraseña de un usuario ya existente
    public static void modificarContraseñaUsuario(String username, String nuevaContraseña) {
        // Hashear la nueva contraseña
        String hashedPassword = BCrypt.hashpw(nuevaContraseña, BCrypt.gensalt());

        // SQL para actualizar la contraseña del usuario
        String sql = "UPDATE Usuarios SET credenciales = ? WHERE username = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los valores para el UPDATE
            stmt.setString(1, hashedPassword);  // Establecer la nueva contraseña hasheada
            stmt.setString(2, username);  // Establecer el username del usuario que queremos modificar

            // Ejecutar el UPDATE
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Contraseña del usuario " + username + " modificada correctamente.");
            } else {
                System.out.println("Error al modificar la contraseña o usuario no encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar la contraseña: " + e.getMessage());
        }
    }
}



