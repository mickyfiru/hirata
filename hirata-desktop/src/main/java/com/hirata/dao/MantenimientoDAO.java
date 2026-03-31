package com.hirata.dao;

import com.hirata.model.Mantenimiento;
import com.hirata.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoDAO {

    public List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT id, descripcion, fecha, estado FROM mantenimientos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mantenimiento mantenimiento = new Mantenimiento(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("estado")
                );
                lista.add(mantenimiento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int contarMantenimientos() {
        String sql = "SELECT COUNT(*) FROM mantenimientos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
