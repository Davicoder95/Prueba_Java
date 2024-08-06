package com.riwi.persistence.configDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
        public static Connection conexion = null;// se inicializa la conexion null


    // se crea el metodo para abrir la conexion
        public static Connection openConnection(){

            //Credenciales para ña conexion con bd
            String URL = "jdbc:mysql://localhost:3306/RiwiAcademy";
            String user = "root";
            String password = "Rlwl2023.";

            // se crea try{} por que la conexion puede fallar
            try {
                conexion = DriverManager.getConnection(URL, user, password);
                JOptionPane.showMessageDialog(null,"Conexion exitosa");

            } catch (SQLException error) {
                throw new RuntimeException(error.getMessage());
            }

            //En caso de que no falle se retorna la conexion
            return conexion;
        }

        //Se crea metodo para cerrar la conexion
        public static void closeConnection(){

            if (conexion != null){

                try {
                    conexion.close();
                    JOptionPane.showMessageDialog(null,"Conexion cerrada exitosamente");
                } catch (SQLException error) {
                    throw new RuntimeException(error.getMessage());
                }
            }
        }

    }


