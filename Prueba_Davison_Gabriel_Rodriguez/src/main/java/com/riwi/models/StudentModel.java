package com.riwi.models;

import com.riwi.entities.StudentEntity;
import com.riwi.persistence.configDB.ConfigDB;
import com.riwi.persistence.iModel.InterfaceStudentModel;
import enums.EnumStatus;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*Se inicializa la conexion de la bd y se abre para ejecutar el sql, se  crea la consulta sql y se ejecuta con el prepared statement , dependiento del caso de setean los datos y se retornar
 * esto se hace dentro de un try catch ya que puede fallar, para finalizar se cierra la conexion con la bd para no consumir datos para siempre  :v*/
public class StudentModel implements InterfaceStudentModel {
    @Override
    public StudentEntity create(StudentEntity request) {
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO student (name,last_name,email,status,document) VALUES(?,?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1,request.getName());
                statement.setString(2,request.getLastName());
                statement.setString(3,request.getEmail());
                statement.setString(4,request.getStatus().name());
                statement.setInt(5,request.getDocument());

                // se ejecuta el query
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();// se obtiene el id generado

            while (resultSet.next()){
                int idGenerate= resultSet.getInt(1);
                request.setIdStudent(idGenerate);
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
            String sql = "DELETE FROM student WHERE id_student =?;";
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

        StudentEntity student= null;
        try {
            String sql = "select * from student where id_student=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.execute();

            ResultSet resutl = statement.getResultSet();

            while(resutl.next()){
                student =new StudentEntity(
                        resutl.getInt("id_student"),
                        resutl.getString("name"),
                        resutl.getString("last_name"),
                        resutl.getString("email"),
                        EnumStatus.valueOf(resutl.getString("status")),
                        resutl.getInt("document")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigDB.closeConnection();
        return student;

    }

    @Override
    public StudentEntity update(StudentEntity request, Integer id) {
        Connection connection = ConfigDB.openConnection();
        String sql= "UPDATE student SET name=?, last_name=?, email=?, status=?,document=? WHERE id_student=?; ";

        Boolean status= false;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,request.getName());
            statement.setString(2,request.getLastName());
            statement.setString(3, request.getEmail());
            statement.setString(4,request.getStatus().name());
            statement.setInt(5,request.getDocument());
            statement.setInt(6,id);

            int verify= statement.executeUpdate();
            if (verify==1){
                JOptionPane.showMessageDialog(null,"Updated student ");
                status=true;

            }else{
                JOptionPane.showMessageDialog(null,"Student not found");
            }
            request.setIdStudent(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return request;
    }

    @Override
    public List<StudentEntity> readAll(int size, int numberPage) {
            List<StudentEntity> studentEntities = new ArrayList<>();
            Connection connection = ConfigDB.openConnection();

            try{
                String sql = "SELECT * FROM student WHERE status='ACTIVE' LIMIT ? OFFSET ? ; ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, size);
                preparedStatement.setInt(2, (numberPage-1)*size);

                ResultSet result = preparedStatement.executeQuery();
                while (result.next()){
                    StudentEntity student = new StudentEntity(
                            result.getInt("id_student"),
                            result.getString("name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            EnumStatus.valueOf(result.getString("status")),
                            result.getInt("document")
                    );
                    studentEntities.add(student);
                }
                preparedStatement.close();
                result.close();
            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }finally {
               ConfigDB.closeConnection();
            }
            return studentEntities;
    }

    @Override
    public Object readEmail(String email) {

        Connection connection = ConfigDB.openConnection();

        StudentEntity student= null;
        try {
            String sql = "SELECT * FROM student WHERE email like ? ;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,email);
            statement.execute();

            ResultSet resutl = statement.getResultSet();

            while(resutl.next()){
                student =new StudentEntity(
                        resutl.getInt("id_student"),
                        resutl.getString("name"),
                        resutl.getString("last_name"),
                        resutl.getString("email"),
                        EnumStatus.valueOf(resutl.getString("status")),
                        resutl.getInt("document")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigDB.closeConnection();
        return student;
    }


}
