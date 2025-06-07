  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controlador.ControladorRegistroCita;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class regCita extends JPanel{

    // Componentes de interfaz
    private JTextField txtBusquedaPaciente;
    private JTextField txtPaciente;
    private JComboBox<String> comboEspecialidad;
    private JComboBox<String> comboMedico;
    private JTextField txtFecha;
    private JComboBox<String> comboHora;
    private JTextArea txtObservaciones;
    private JButton btnBuscarPaciente;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public regCita() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearPanelPaciente(), BorderLayout.NORTH);
        add(crearPanelCita(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);

        inicializarComponentes();
    }

    private JPanel crearPanelPaciente() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("DNI:"), gbc);
        txtBusquedaPaciente = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtBusquedaPaciente, gbc);

        btnBuscarPaciente = new JButton("Buscar");
        btnBuscarPaciente.addActionListener(e -> buscarPaciente());
        gbc.gridx = 2;
        panel.add(btnBuscarPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Paciente:"), gbc);
        txtPaciente = new JTextField(30);
        txtPaciente.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(txtPaciente, gbc);

        return panel;
    }

    private JPanel crearPanelCita() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Detalles de la Cita"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Especialidad:"), gbc);
        comboEspecialidad = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(comboEspecialidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Médico:"), gbc);
        comboMedico = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(comboMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fecha:"), gbc);
        txtFecha = new JTextField(15);
        txtFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        txtFecha.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtFecha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Hora:"), gbc);
        comboHora = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(comboHora, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Observaciones:"), gbc);
        txtObservaciones = new JTextArea(3, 30);
        txtObservaciones.setLineWrap(true);
        gbc.gridx = 1;
        panel.add(new JScrollPane(txtObservaciones), gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnRegistrar = new JButton("Registrar Cita");
        btnRegistrar.addActionListener(e -> registrarCita());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> limpiarFormulario());

        panel.add(btnRegistrar);
        panel.add(btnCancelar);

        return panel;
    }

    private void inicializarComponentes() {
        String[] especialidades = {"Medicina General", "Cardiología", "Pediatría", "Dermatología"};
        for (String esp : especialidades) {
            comboEspecialidad.addItem(esp);
        }

        comboEspecialidad.addActionListener(e -> actualizarMedicos());

        String[] horas = {"08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00"};
        for (String hora : horas) {
            comboHora.addItem(hora);
        }
    }

    private void actualizarMedicos() {
        comboMedico.removeAllItems();
        String especialidad = (String) comboEspecialidad.getSelectedItem();

        switch (especialidad) {
            case "Medicina General" -> {
                comboMedico.addItem("Dr. Juan Pérez");
                comboMedico.addItem("Dra. María García");
            }
            case "Cardiología" -> {
                comboMedico.addItem("Dr. Carlos Rodríguez");
                comboMedico.addItem("Dra. Ana Martínez");
            }
            case "Pediatría" -> {
                comboMedico.addItem("Dr. Matt Murdock");
                comboMedico.addItem("Dra. Selina Kyle");
            }
            case "Dermatología" -> {
                comboMedico.addItem("Dr. Paulo Dybala");
                comboMedico.addItem("Dra. Jenifer Flores");
            }
        }
    }

    private void buscarPaciente() {
        String dni = txtBusquedaPaciente.getText().trim();
        if (dni.isEmpty()) {
            mostrarError("Ingrese un DNI o número de historia clínica");
            return;
        }

        String paciente = ControladorRegistroCita.buscarPacienteEnBD(dni);
        if (paciente != null) {
            txtPaciente.setText(paciente);
            btnRegistrar.setEnabled(true);
        } else {
            mostrarError("Paciente no encontrado");
            txtPaciente.setText("");
            btnRegistrar.setEnabled(false);
        }
    }

    private void registrarCita() {
        if (txtPaciente.getText().isEmpty()) {
            mostrarError("Debe buscar un paciente");
            return;
        }

        if (comboEspecialidad.getSelectedIndex() == -1 || comboMedico.getSelectedIndex() == -1) {
            mostrarError("Seleccione especialidad y médico");
            return;
        }

        String dni = txtBusquedaPaciente.getText();
        String paciente = txtPaciente.getText();
        String especialidad = (String) comboEspecialidad.getSelectedItem();
        String medico = (String) comboMedico.getSelectedItem();
        String fecha = txtFecha.getText();
        String hora = (String) comboHora.getSelectedItem();
        String observaciones = txtObservaciones.getText();

        ControladorRegistroCita controlador = new ControladorRegistroCita();
        boolean exito = controlador.registrarCitaEnBD(dni, paciente, especialidad, medico, fecha, hora, observaciones);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Cita registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } else {
            mostrarError("Error al registrar la cita");
        }
    }

    private void limpiarFormulario() {
        txtBusquedaPaciente.setText("");
        txtPaciente.setText("");
        comboEspecialidad.setSelectedIndex(-1);
        comboMedico.removeAllItems();
        comboHora.setSelectedIndex(-1);
        txtObservaciones.setText("");
        btnRegistrar.setEnabled(false);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Métodos getters si los necesitas públicamente

    public JTextField getTxtBusquedaPaciente() { return txtBusquedaPaciente; }
    public JTextField getTxtPaciente() { return txtPaciente; }
    public JComboBox<String> getComboEspecialidad() { return comboEspecialidad; }
    public JComboBox<String> getComboMedico() { return comboMedico; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<String> getComboHora() { return comboHora; }
    public JTextArea getTxtObservaciones() { return txtObservaciones; }
    public JButton getBtnBuscarPaciente() { return btnBuscarPaciente; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnCancelar() { return btnCancelar; }

}
