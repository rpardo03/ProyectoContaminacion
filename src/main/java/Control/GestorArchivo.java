package Control;

import Modelo.Registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestorArchivo {

    public GestorArchivo() {

    }

    public ArrayList<Registro> leerDatos(String ruta) {
        ArrayList<Registro> registros = new ArrayList<>(900);
        String linea = "";
        try {
            FileReader fileReader = new FileReader(new File(ruta));
            BufferedReader buffer = new BufferedReader(fileReader);
            while ((linea = buffer.readLine()) != null) {
                registros.add(crearRegistro(linea));
            }
            registros.trimToSize();
            buffer.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado.");
            return null;
        }
        return registros;
    }

    private Registro crearRegistro(String linea) {
        String[] atributos = linea.split(",");
        Registro r = new Registro();
        r.setSector(atributos[0]);
        r.setIdSensor(Integer.parseInt(atributos[1]));
        r.setFecha(LocalDate.parse(atributos[2],DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        r.setHora(LocalTime.parse(atributos[3]));
        r.setPm10(Double.parseDouble(atributos[4]));
        r.setPm25(Double.parseDouble(atributos[5]));
        r.setHum(Double.parseDouble(atributos[6]));
        r.setTemp(Double.parseDouble(atributos[7]));
        return r;
    }


}
