package com.hirata.controller;

import com.hirata.dao.CamionDAO;
import com.hirata.model.Camion;
import com.hirata.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CamionController {

    @FXML
    private TableView<Camion> tableCamiones;

    @FXML
    private TableColumn<Camion, Integer> colId;

    @FXML
    private TableColumn<Camion, String> colPlaca;

    @FXML
    private TableColumn<Camion, String> colModelo;

    @FXML
    private TableColumn<Camion, Integer> colAnio;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAnio;

    @FXML
    private Label lblMensaje;

    private final CamionDAO camionDAO = new CamionDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colPlaca.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlaca()));
        colModelo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));
        colAnio.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAnio()).asObject());

        cargarCamiones();
    }

    @FXML
    public void guardarCamion() {
        String placa = txtPlaca.getText();
        String modelo = txtModelo.getText();
        String anioTexto = txtAnio.getText();

        if (placa.isBlank() || modelo.isBlank() || anioTexto.isBlank()) {
            lblMensaje.setText("Completa todos los campos");
            return;
        }

        try {
            int anio = Integer.parseInt(anioTexto);
            Camion camion = new Camion(placa, modelo, anio);

            if (camionDAO.guardarCamion(camion)) {
                lblMensaje.setText("Camión guardado correctamente");
                txtPlaca.clear();
                txtModelo.clear();
                txtAnio.clear();
                cargarCamiones();
            } else {
                lblMensaje.setText("No se pudo guardar");
            }
        } catch (NumberFormatException e) {
            lblMensaje.setText("El año debe ser numérico");
        }
    }

    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 700, 500);
    }

    private void cargarCamiones() {
        ObservableList<Camion> datos = FXCollections.observableArrayList(camionDAO.listarCamiones());
        tableCamiones.setItems(datos);
    }
}
