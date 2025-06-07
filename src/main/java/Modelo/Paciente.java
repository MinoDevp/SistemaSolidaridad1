package Modelo;

public class Paciente {

    private String nombre, apellidos, dni, fechaNacimiento,
            telefono, direccion, grupoSanguineo, alergias, antecedentes;

    public Paciente(String nombre, String apellidos, String dni, String fechaNacimiento, String telefono, String direccion, String grupoSanguineo, String alergias, String antecedentes) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
        this.antecedentes = antecedentes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

}
