package Modelo;

public class Registro {

    private String sector;
    private double pm10;
    private double pm25;
    private double temperatura;
    private double humedad;
    private int idSensor;
    private String fecha;
    private String hora;

    public Registro() {

    }

    public Registro(String sector, int idSensor, String fecha, String hora, double pm10, double pm25, double temperatura, double humedad) {
        this.sector = sector;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.idSensor = idSensor;
        this.fecha = fecha;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Sector:" + sector + " Dispositivo:" + idSensor + " Fecha:" + fecha + " Hora:" + hora + " PM10:" + pm10 + " PM2.5:" + pm25 + " Humedad:" + humedad + " Temperatura:" + temperatura;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
