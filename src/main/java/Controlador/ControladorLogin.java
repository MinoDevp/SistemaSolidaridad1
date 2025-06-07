package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vistas.Login;
import Vistas.winPrincipal;
import Vistas.VistaGestionUsuario; // Suponiendo que tienes una vista de administración para el admin

public class ControladorLogin {
    private Login vistaLogin;

    // Constructor del controlador que recibe la vista Login
    public ControladorLogin(Login vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    // Método que se llama cuando el usuario hace clic en "LOGIN"
    public void autenticar() {
    // Obtener los datos de la vista
    String username = vistaLogin.getTxtUsername().getText().trim();
    String password = new String(vistaLogin.getPasswordField().getPassword()).trim();

    // Validar si los campos están vacíos
    if (username.isEmpty() || password.isEmpty()) {
        vistaLogin.getlblMensajeError().setText("Campos vacíos");
        return;
    }

    // Crear una instancia de UsuarioDAO para validar el usuario
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuario = usuarioDAO.validarUsuario(username, password);

    // Verificar si el usuario es nulo (lo que significa que no se encontró o las credenciales son incorrectas)
    if (usuario == null) {
        vistaLogin.getlblMensajeError().setText("Usuario o contraseña incorrectos");
    } else {
        // Si el login es exitoso, se limpia el mensaje de error
        vistaLogin.getlblMensajeError().setText(""); 
        System.out.println("Login exitoso para el usuario: " + usuario.getUsername());

        // Según el rol, redirigir a diferentes vistas
        String rol = usuario.getRol();  // Obtenemos el rol del usuario

        // Asegúrate de que la comparación esté bien hecha (ten cuidado con las mayúsculas/minúsculas)
        if (rol != null) {
            if (rol.equalsIgnoreCase("admin")) {
                // Si el rol es admin, redirigir al panel de administración
                VistaGestionUsuario ventanaAdmin = new VistaGestionUsuario();
                ventanaAdmin.setVisible(true);  // Hacer visible la ventana de administración
            } else if (rol.equalsIgnoreCase("recepcionista")) {
                // Si es recepcionista, redirigir a la vista principal
                winPrincipal menuPrincipal = new winPrincipal();
                menuPrincipal.setVisible(true);  // Hacer visible la ventana principal
            } else if (rol.equalsIgnoreCase("medico")) {
                // Si es médico, también redirigir a la vista principal
                winPrincipal menuPrincipal = new winPrincipal();
                menuPrincipal.setVisible(true);  // Hacer visible la ventana principal
            } else {
                vistaLogin.getlblMensajeError().setText("Rol desconocido.");
            }
        }

        // Ocultar la ventana de login
        vistaLogin.setVisible(false);
    }
}

    
}

