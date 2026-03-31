package com.hirata.controller;

import com.hirata.dao.MantenimientoDAO;
import com.hirata.model.Mantenimiento;
import com.hirata.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MantenimientoController {

    @FXML
    private TableView<Mantenimiento> tableMantenimientos;

    @FXML
    private TableColumn<Mantenimiento, Integer> colId;

    @FXML
    private TableColumn<Mantenimiento, String> colDescripcion;

    @FXML
    private TableColumn<Mantenimiento, String> colFecha;

    @FXML
    private TableColumn<Mantenimiento, String> colEstado;

    private final MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFecha()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        tableMantenimientos.setItems(FXCollections.observableArrayList(mantenimientoDAO.listarMantenimientos()));
    }

    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 700, 500);
    }
}
