
package Modelo;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarUsuario(String username, String password) {
        String sql = "SELECT idUsuario, username, credenciales, rol FROM Usuarios WHERE username = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);  // Usamos el username para buscar al usuario
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("credenciales");
                    String rol = rs.getString("rol");
                    int idUsuario = rs.getInt("idUsuario");

                    // Comparar la contraseña ingresada con la almacenada (hasheada)
                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        // Si las contraseñas coinciden, se crea el objeto usuario con sus datos
                        Usuario usuario = new Usuario(idUsuario, username, rol);
                        return usuario;
                        
                    } else {
                        System.out.println("contraseña incorrecta");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return null;  // Si no se encuentra el usuario o las contraseñas no coinciden
    }
}




