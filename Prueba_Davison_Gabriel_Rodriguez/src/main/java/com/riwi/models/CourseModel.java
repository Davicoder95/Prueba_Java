package com.riwi.models;

import com.riwi.entities.CourseEntity;
import com.riwi.persistence.configDB.ConfigDB;
import com.riwi.persistence.iModel.InterfaceCouserModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*Se inicializa la conexion de la bd y se abre para ejecutar el sql, se  crea la consulta sql y se ejecuta con el prepared statement , dependiento del caso de setean los datos y se retornar
 * esto se hace dentro de un try catch ya que puede fallar, para finalizar se cierra la conexion con la bd para no consumir datos para siempre  :v*/
public class CourseModel implements InterfaceCouserModel {
    @Override
    public CourseEntity create(CourseEntity request) {
        // creamos la conexion y la abrimos
        Connection connection = ConfigDB.openConnection();
        // se crea el sql para hacer la consulta
        String sql = "INSERT INTO course (name_course) VALUES(?);";
        // se utiliza try ya que la consulta podria fallar
        try {
            // preparedStatement nos ejecuta la consultra
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1,request.getNameCourse());

            // se ejecuta el query
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();// se obtiene el id generado

            while (resultSet.next()){
                int idGenerate= resultSet.getInt(1);
                request.setIdCourse(idGenerate);//se setea el id generado por el id del curso
            }
        } catch (SQLException e) {
            // si el query falla salta a este error
            throw new RuntimeException(e);
        }
        ConfigDB.closeConnection();// cerramos la conexion
        return request;// retornamos el curso
    }


    public boolean deleteif(Integer id){
        Connection connection = ConfigDB.openConnection();
        Boolean status;

        try{
            String sql = "SELECT * FROM inscription INNER JOIN inscription on inscription.id_course != course.id_course;";
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
    public boolean delete(Integer id) {
        // abrimos conexion
        Connection connection = ConfigDB.openConnection();
        // se instancia una bandera
        Boolean status;

        try{
            // se hace la consulta
            String sql = "DELETE FROM course WHERE id_course =? ;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);// id a eliminar
            status = statement.execute();//status verificara si la consulta fue exitosa

        } catch (SQLException e) {// si no es exitosa salta a elte catch
            throw new RuntimeException(e);
        }
        ConfigDB.closeConnection();
        return status;// si es exitosa se retorna
    }

    @Override
    public Object read(Integer id) {
        Connection connection = ConfigDB.openConnection();

        CourseEntity course = null;
        try {
            String sql = "SELECT * FROM course WHERE id_course =?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            statement.execute();

            ResultSet resutl = statement.getResultSet();

            while(resutl.next()){
                course =new CourseEntity(
                        resutl.getInt("id_course"),
                        resutl.getString("name_course")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigDB.closeConnection();
        return course;
    }

    @Override
    public CourseEntity update(CourseEntity request, Integer id) {
        Connection connection = ConfigDB.openConnection();
        String sql= "UPDATE course SET name_course =?WHERE id_course=?; ";

        Boolean status= false;
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,request.getNameCourse());
            statement.setInt(2,id);

            int verify= statement.executeUpdate();
            if (verify==1){
                JOptionPane.showMessageDialog(null,"Updated course ");
                status=true;

            }else{
                JOptionPane.showMessageDialog(null,"Course not found");
            }
            request.setIdCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return request;
    }

    @Override
    public List<CourseEntity> readAll(int size, int numberPage) {
        List<CourseEntity> courseEntities = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM course LIMIT ? OFFSET ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (numberPage-1)*size);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                CourseEntity course = new CourseEntity(
                       result.getInt("id_course"),
                        result.getString("name_course")
                );
                courseEntities.add(course);
            }
            preparedStatement.close();
            result.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return courseEntities;
    }

    @Override
    public Object readEmail(Integer integer) {
        return null;
    }
}
