package Modelo;

import java.sql.*;

public class BaseDatoSQL {

    private String driver_JDBC;
    private String rutaBD;
    private String usuario;
    private String contrasenia;
    private Connection connection;
    private Statement statement;

    public BaseDatoSQL() {

    }

    public BaseDatoSQL(String usuario, String contrasenia) {
        this.driver_JDBC = "com.mysql.jdbc.Driver";
        this.rutaBD = "jdbc:mysql://localhost/Contaminacion_Araucania";
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    private void establecerConexion() {
        try{
            Class.forName(this.driver_JDBC);
            this.connection = DriverManager.getConnection(rutaBD,usuario,contrasenia);
            this.statement = this.connection.createStatement();
        }catch (Exception e){
            System.out.println("No se ha podido establecer conexión con la base de datos, inténtelo nuevamente.");
        }
    }

    private void cerrarRecursos() {
        try{
            this.statement.close();
            this.connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void hacerConsulta() {
        establecerConexion();
        try{
            ResultSet rs = this.statement.executeQuery("Select * from Registro");
            while(rs.next()){
                double valorPM10 = rs.getDouble("");
                double valorPM25 = rs.getDouble("");
                double valorTemp = rs.getDouble("");
                double valorHum = rs.getDouble("");
                String hora = rs.getString("");
                String fecha = rs.getString("");
                System.out.println("mostrar datos");
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void aniadirElementos() {

    }
}
