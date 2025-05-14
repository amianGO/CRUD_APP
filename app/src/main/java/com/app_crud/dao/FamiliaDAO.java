package com.app_crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app_crud.db.Conection;
import com.app_crud.model.Familia;
import com.app_crud.model.Funcionario;

public class FamiliaDAO {
    
    public List<Familia> obtenerFamiliares(int funcionarioId){
        
        List<Familia> familiaries = new ArrayList<>();
        String sql = "SELECT * FROM familia WHERE funcionario_id = ?";

        try (Connection con = Conection.Conexion();
            PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Familia F = new Familia();
                F.setId(rs.getInt("id"));
                F.setNombre(rs.getString("nombre"));
                F.setParentesco(rs.getString("parentesco"));
                F.setSexo(rs.getString("sexo"));
                F.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

                familiaries.add(F);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return familiaries;
    }

    public boolean agregarFamiliar(Funcionario funcionario, Familia familia){
        String sql = "INSERT INTO familia (funcionario_id, nombre, parentesco, sexo, fechaNacimiento) VALUES (?,?,?,?,?)";

        try (Connection con = Conection.Conexion();
            PreparedStatement stmt = con.prepareStatement(sql);){
            

            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, familia.getNombre());
            stmt.setString(3, familia.getParentesco());
            stmt.setString(4, familia.getSexo());
            stmt.setDate(5, java.sql.Date.valueOf(familia.getFechaNacimiento()));

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
