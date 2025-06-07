package Modelo;

public class Usuario {
    private int idUsuario;
    private String username;
    private String rol;

    // Constructor que acepta idUsuario, username y rol
    public Usuario(int idUsuario, String username, String rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.rol = rol;
    }

    // Getters y setters para los atributos
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}



