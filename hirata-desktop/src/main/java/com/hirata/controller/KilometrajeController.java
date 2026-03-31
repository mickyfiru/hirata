package com.hirata.controller;

import com.hirata.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KilometrajeController {

    @FXML
    private TextField txtCamion;

    @FXML
    private TextField txtKilometraje;

    @FXML
    private Label lblMensaje;

    @FXML
    public void registrarKilometraje() {
        String camion = txtCamion.getText();
        String kilometraje = txtKilometraje.getText();

        if (camion.isBlank() || kilometraje.isBlank()) {
            lblMensaje.setText("Completa todos los campos");
            return;
        }

        lblMensaje.setText("Kilometraje registrado correctamente");
    }

    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 700, 500);
    }
}
