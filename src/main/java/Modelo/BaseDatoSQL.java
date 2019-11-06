package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDatoSQL {

    private String driver_JDBC;
    private String rutaBD;
    private String usuario;
    private String contrasenia;
    private Connection connection;
    private Statement statement;


    public BaseDatoSQL() {
        this.driver_JDBC = "com.mysql.cj.jdbc.Driver";
        this.rutaBD = "jdbc:mysql://localhost/Contaminacion_Araucania";
        this.usuario = "root";
        this.contrasenia = "";
    }

    private void establecerConexion() {
        try {
            Class.forName(this.driver_JDBC);
            this.connection = DriverManager.getConnection(rutaBD, usuario, contrasenia);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            System.out.println("No se ha podido establecer conexión con la base de datos, inténtelo nuevamente.");
        }
    }

    private void cerrarRecursos() {
        try {
            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hacerConsulta() {
        establecerConexion();
        try {
            ResultSet rs = this.statement.executeQuery("Select * from Registro");
            while (rs.next()) {
                double valorPM10 = rs.getDouble("");
                double valorPM25 = rs.getDouble("");
                double valorTemp = rs.getDouble("");
                double valorHum = rs.getDouble("");
                String hora = rs.getString("");
                String fecha = rs.getString("");
                System.out.println("mostrar datos");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aniadirElementosToRegistro(ArrayList<Registro> r) {
        int idSensor = 0;
        establecerConexion();
        try {
            for (int i = 0; i < r.size(); i++) {
                String sql = "Insert into Registro " + " VALUES (" + r.get(i).getFecha() + "," + r.get(i).getHora() + "," + r.get(i).getPm10() + ","
                        + r.get(i).getPm25() + "," + r.get(i).getTemperatura() + "," + r.get(i).getHumedad() + r.get(i).getSector() + ")";
                this.statement.executeUpdate(sql);
                if (r.get(i).getIdSensor() != idSensor) {
                    if (!comprobarIdSensorAgregado(r.get(i).getIdSensor())) {
                        String sql2 = "Insert into Sensor " + " VALUES (" + r.get(i).getIdSensor() + ")";
                        this.statement.executeUpdate(sql2);
                        idSensor = r.get(i).getIdSensor();
                    }
                }
            }
            cerrarRecursos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean comprobarIdSensorAgregado(int idSensorComp) {
        establecerConexion();
        try {
            ResultSet rs = this.statement.executeQuery("Select * from Sensor");
            while (rs.next()) { // se puede implementar una busqueda binaria?
                int idSensor = rs.getInt("idSensor");
                if (idSensorComp == idSensor) {
                    rs.close();
                    return true;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
