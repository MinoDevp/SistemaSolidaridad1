package Controlador;

import Modelo.Paciente;
import Modelo.PacienteDAO;
import Vistas.regPacie;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroPaciente {
 private regPacie vista;

    public ControladorRegistroPaciente(regPacie vista) {
        this.vista = vista;

        this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente();
            }
        });
    }

    private void registrarPaciente() {
        String nombre = vista.getTxtNombre().getText().trim();
        String apellidos = vista.getTxtApellidos().getText().trim();
        String dni = vista.getTxtDNI().getText().trim();
        String fechaNacimiento = vista.getTxtFechaNacimiento().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();
        String direccion = vista.getTxtDireccion().getText().trim();
        String grupoSanguineo = (String) vista.getComboGrupoSanguineo().getSelectedItem();


        StringBuilder alergias = new StringBuilder();
        for (int i = 0; i < vista.getPanelAlergias().getComponentCount(); i++) {
            JCheckBox checkBox = (JCheckBox) vista.getPanelAlergias().getComponent(i);
            if (checkBox.isSelected()) {
                if (alergias.length() > 0) alergias.append(", ");
                alergias.append(checkBox.getText());
            }
        }

        String antecedentes = vista.getTxtAntecedentes().getText().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente paciente = new Paciente(nombre, apellidos, dni, fechaNacimiento, telefono, direccion, grupoSanguineo, alergias.toString(), antecedentes);
        PacienteDAO dao = new PacienteDAO();

        if (dao.registrarPaciente(paciente)) {
            JOptionPane.showMessageDialog(vista, "Paciente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar paciente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
