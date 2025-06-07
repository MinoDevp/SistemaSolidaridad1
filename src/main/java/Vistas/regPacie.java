/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Daniel
 */
public class regPacie extends JPanel{
    
    private JTextField txtNombre, txtApellidos, txtDNI, txtTelefono;
    private JTextArea txtDireccion, txtAntecedentes;
    private JFormattedTextField txtFechaNacimiento;
    private JComboBox<String> comboGrupoSanguineo;
    private JButton btnRegistrar, btnCancelar;
    private JPanel panelAlergias;

    public regPacie() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(crearPanelDatosPersonales(), gbc);

        gbc.gridy = 1;
        mainPanel.add(crearPanelDatosMedicos(), gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(crearPanelBotones(), gbc);

        JScrollPane scroll = new JScrollPane(mainPanel);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearPanelDatosPersonales() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Apellidos
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Apellidos:"), gbc);
        txtApellidos = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtApellidos, gbc);

        // DNI
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("DNI:"), gbc);
        txtDNI = new JTextField(10);
        gbc.gridx = 1;
        panel.add(txtDNI, gbc);

        // Fecha de nacimiento
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        try {
            MaskFormatter formatter = new MaskFormatter("####/##/##");
            txtFechaNacimiento = new JFormattedTextField(formatter);
        } catch (Exception e) {
            txtFechaNacimiento = new JFormattedTextField();
        }
        gbc.gridx = 1;
        panel.add(txtFechaNacimiento, gbc);

        // Teléfono
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtTelefono, gbc);

        // Dirección
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Dirección:"), gbc);
        txtDireccion = new JTextArea(3, 20);
        txtDireccion.setLineWrap(true);
        txtDireccion.setWrapStyleWord(true);
        JScrollPane scrollDireccion = new JScrollPane(txtDireccion);
        gbc.gridx = 1;
        panel.add(scrollDireccion, gbc);

        return panel;
    }

    private JPanel crearPanelDatosMedicos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos Médicos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Grupo sanguíneo
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Grupo Sanguíneo:"), gbc);
        comboGrupoSanguineo = new JComboBox<>(new String[]{
                "Seleccionar", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        });
        gbc.gridx = 1;
        panel.add(comboGrupoSanguineo, gbc);

        // Alergias
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Alergias:"), gbc);
        panelAlergias = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (String al : new String[]{"Penicilina", "Látex", "Aspirina", "Otras"}) {
            panelAlergias.add(new JCheckBox(al));
        }
        gbc.gridx = 1;
        panel.add(panelAlergias, gbc);

        // Antecedentes
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Antecedentes:"), gbc);
        txtAntecedentes = new JTextArea(4, 20);
        txtAntecedentes.setLineWrap(true);
        txtAntecedentes.setWrapStyleWord(true);
        JScrollPane scrollAntecedentes = new JScrollPane(txtAntecedentes);
        gbc.gridx = 1;
        panel.add(scrollAntecedentes, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrar());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> limpiarCampos());

        panel.add(btnRegistrar);
        panel.add(btnCancelar);

        return panel;
    }

    private void registrar() {
        if (txtNombre.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()
                || txtDNI.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios", "Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtDNI.getText().matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente\nNº Historia Clínica: " + generarNumeroHistoria(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDNI.setText("");
        txtFechaNacimiento.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtAntecedentes.setText("");
        comboGrupoSanguineo.setSelectedIndex(0);
        for (Component comp : panelAlergias.getComponents()) {
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setSelected(false);
            }
        }
    }

    private String generarNumeroHistoria() {
        return "HC" + (System.currentTimeMillis() % 100000);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellidos() { return txtApellidos; }
    public JTextField getTxtDNI() { return txtDNI; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextArea getTxtDireccion() { return txtDireccion; }
    public JTextArea getTxtAntecedentes() { return txtAntecedentes; }
    public JFormattedTextField getTxtFechaNacimiento() { return txtFechaNacimiento; }
    public JComboBox<String> getComboGrupoSanguineo() { return comboGrupoSanguineo; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JPanel getPanelAlergias() { return panelAlergias; }
    
}
