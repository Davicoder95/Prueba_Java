package com.riwi.models;

import com.riwi.entities.InscriptionEntity;
import com.riwi.persistence.configDB.ConfigDB;
import com.riwi.persistence.iModel.InterfaceInscriptionModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*Se inicializa la conexion de la bd y se abre para ejecutar el sql, se  crea la consulta sql y se ejecuta con el prepared statement , dependiento del caso de setean los datos y se retornar
 * esto se hace dentro de un try catch ya que puede fallar, para finalizar se cierra la conexion con la bd para no consumir datos para siempre  :v*/
public class InscriptionModel implements InterfaceInscriptionModel {
    @Override
    public InscriptionEntity create(InscriptionEntity request) {
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO inscription (id_student,id_course) VALUES(?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1,request.getIdStudent());
            statement.setInt(2,request.getIdCourse());

            // se ejecuta el query
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();// se obtiene el id generado

            while (resultSet.next()){
                int idGenerate= resultSet.getInt(1);
                request.setIdInscription(idGenerate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConfigDB.closeConnection();
        return request;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = ConfigDB.openConnection();
        Boolean status;

        try{
            String sql = "DELETE FROM inscription WHERE id_inscription =?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            status = statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConfigDB.closeConnection();
        return status;
    }

    @Override
    public Object read(Integer id) {
        Connection connection = ConfigDB.openConnection();

       InscriptionEntity inscription = null;
        try {
            String sql = "SELECT * FROM inscription  WHERE id_inscription =?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            statement.execute();

            ResultSet resutl = statement.getResultSet();

            while(resutl.next()){
                inscription=new InscriptionEntity(
                       resutl.getInt("id_inscription"),
                        resutl.getInt("id_student"),
                        resutl.getInt("id_course")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigDB.closeConnection();
        return inscription;
    }

    @Override
    public InscriptionEntity update(InscriptionEntity request, Integer id) {
        Connection connection = ConfigDB.openConnection();
        String sql= "UPDATE inscription SET id_student=?, id_course=? WHERE id_inscription=?; ";

        Boolean status= false;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,request.getIdStudent());
            statement.setInt(2,request.getIdCourse());
            statement.setInt(3,id);

            int verify= statement.executeUpdate();
            if (verify==1){
                JOptionPane.showMessageDialog(null,"Updated inscription ");
                status=true;

            }else{
                JOptionPane.showMessageDialog(null,"Inscription not found");
            }
            request.setIdCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return request;
    }

    @Override
    public Object readEmail(Integer integer) {
        return null;
    }
}
