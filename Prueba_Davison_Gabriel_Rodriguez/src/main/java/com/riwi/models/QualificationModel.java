package com.riwi.models;

import com.riwi.entities.QualificationEntity;
import com.riwi.persistence.configDB.ConfigDB;
import com.riwi.persistence.iModel.InterfaceQualificationModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*Se inicializa la conexion de la bd y se abre para ejecutar el sql, se  crea la consulta sql y se ejecuta con el prepared statement , dependiento del caso de setean los datos y se retornar
* esto se hace dentro de un try catch ya que puede fallar, para finalizar se cierra la conexion con la bd para no consumir datos para siempre  :v*/
public class QualificationModel implements InterfaceQualificationModel {
    @Override
    public QualificationEntity create(QualificationEntity request) {
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO qualification(description,qualification,id_course,id_student) VALUES(?, ?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
         statement.setString(1,request.getDescription());
         statement.setInt(2,request.getQualification());
         statement.setInt(3,request.getIdCourse());
         statement.setInt(4,request.getIdStudent());

            // se ejecuta el query
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();// se obtiene el id generado

            while (resultSet.next()){
                int idGenerate= resultSet.getInt(1);
                request.setIdQualification(idGenerate);
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
            String sql = "DELETE FROM qualification WHERE id_qualification =?;";
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

        QualificationEntity qualification = null;
        try {
            String sql = "SELECT * FROM qualification  WHERE id_qualification=?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            statement.execute();

            ResultSet resutl = statement.getResultSet();

            while(resutl.next()){
               qualification=new QualificationEntity(
                       resutl.getString("description"),
                        resutl.getInt("qualification"),
                       resutl.getInt("id_course"),
                       resutl.getInt("id_student")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigDB.closeConnection();
        return qualification;
    }

    @Override
    public QualificationEntity update(QualificationEntity request, Integer id) {
        Connection connection = ConfigDB.openConnection();
        String sql= "UPDATE qualification SET description=?, qualification=? , id_course=? , id_student=? WHERE id_qualification=?; ";

        Boolean status= false;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, request.getDescription());
            statement.setInt(2,request.getQualification());
            statement.setInt(3,request.getIdCourse());
            statement.setInt(4,request.getIdStudent());
            statement.setInt(5,id);


            int verify= statement.executeUpdate();
            if (verify==1){
                JOptionPane.showMessageDialog(null,"Updated qualification");
                status=true;

            }else{
                JOptionPane.showMessageDialog(null,"Qualification not found");
            }
            request.setIdQualification(id);
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
