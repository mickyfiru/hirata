package com.hirata.controller;

import com.hirata.dao.CamionDAO;
import com.hirata.dao.MantenimientoDAO;
import com.hirata.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblCamiones;

    @FXML
    private Label lblConductores;

    @FXML
    private Label lblMantenimientos;

    @FXML
    private Label lblAlertas;

    private final CamionDAO camionDAO = new CamionDAO();
    private final MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();

    @FXML
    public void initialize() {
        lblCamiones.setText(String.valueOf(camionDAO.contarCamiones()));
        lblConductores.setText("0");
        lblMantenimientos.setText(String.valueOf(mantenimientoDAO.contarMantenimientos()));
        lblAlertas.setText("0");
    }

    @FXML
    public void abrirCamiones() {
        SceneManager.loadScene("/view/camiones.fxml", "Hirata - Camiones", 800, 500);
    }

    @FXML
    public void abrirKilometraje() {
        SceneManager.loadScene("/view/kilometraje.fxml", "Hirata - Kilometraje", 600, 400);
    }

    @FXML
    public void abrirMantenimientos() {
        SceneManager.loadScene("/view/mantenimientos.fxml", "Hirata - Mantenimientos", 800, 500);
    }

    @FXML
    public void cerrarSesion() {
        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 400, 300);
    }
}
