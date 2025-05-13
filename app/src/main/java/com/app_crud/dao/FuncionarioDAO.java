package com.app_crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app_crud.db.Conection;
import com.app_crud.model.Funcionario;

public class FuncionarioDAO {
    
    public List<Funcionario> obtenerFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try {
            Connection con = Conection.Conexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setTipoId(rs.getString("tipoId"));
                f.setNumId(rs.getInt("numId"));
                f.setNombre(rs.getString("nombre"));
                f.setApellido(rs.getString("apellido"));
                f.setEstadoCivil(rs.getString("estadoCivil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

                FamiliaDAO familiaDAO = new FamiliaDAO();
                f.setFamilia(familiaDAO.obtenerFamiliares(f.getId()));

                funcionarios.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public boolean agregarFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO funcionarios (tipoId, numId, nombre, apellido, estadoCivil, sexo, direccion, telefono, fechaNacimiento) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            Connection con = Conection.Conexion();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, funcionario.getTipoId());
            stmt.setString(2, String.valueOf(funcionario.getNumId()));
            stmt.setString(3, funcionario.getNombre());
            stmt.setString(4, funcionario.getApellido());
            stmt.setString(5, funcionario.getEstadoCivil());
            stmt.setString(6, funcionario.getSexo());
            stmt.setString(7, funcionario.getDireccion());
            stmt.setString(8, funcionario.getTelefono());
            stmt.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminarFuncionario(int id){
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection con = Conection.Conexion();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }

    public boolean editFuncionario(Funcionario funcionario){
        String sql = "UPDATE funcionarios SET tipoId = ?, numId = ?, nombre = ?, apellido = ?, estadoCivil = ?, sexo = ?, direccion = ?, telefono = ?, fechaNacimiento = ? WHERE id = ?";

        try (Connection con = Conection.Conexion();
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getTipoId());
            stmt.setInt(2, funcionario.getNumId());
            stmt.setString(3, funcionario.getNombre());
            stmt.setString(4, funcionario.getApellido());
            stmt.setString(5, funcionario.getEstadoCivil());
            stmt.setString(6, funcionario.getSexo());
            stmt.setString(7, funcionario.getDireccion());
            stmt.setString(8, funcionario.getTelefono());
            stmt.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));
            stmt.setInt(10, funcionario.getId());

            int rowsUpdate = stmt.executeUpdate();
            return rowsUpdate > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
