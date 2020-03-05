package Control;

import Modelo.Registro;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestorArchivo {

    public GestorArchivo() {

    }

    public void escribirArchivo(ArrayList<Registro> registros, String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < registros.size(); i++) {
                bw.write(registros.get(i).toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Archivo no escontrado.");
        }
    }

    public ArrayList<Registro> leerDatOrig(String ruta) {
        ArrayList<Registro> registros = new ArrayList<>(289000);
        String linea = "";
        int numLineas = 0;
        try {
            FileReader fileReader = new FileReader(new File(ruta));
            BufferedReader buffer = new BufferedReader(fileReader);
            while ((linea = buffer.readLine()) != null) {
                numLineas++;
                if (numLineas > 1) {
                    registros.add(crearRegistroF1(linea));
                }
            }
            registros.trimToSize();
            buffer.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado.");
        }
        return registros;
    }

    private Registro crearRegistroF1(String linea) {
        String[] datosReg = linea.split(",");
        Registro r = new Registro();
        r.setSector(datosReg[0]);
        r.setIdSensor(Integer.parseInt(datosReg[1]));
        r.setFecha(LocalDate.parse(datosReg[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        r.setHora(LocalTime.parse(datosReg[3], DateTimeFormatter.ofPattern("H:mm:ss")));
        r.setPm10(Double.parseDouble(datosReg[4]));
        r.setPm25(Double.parseDouble(datosReg[5]));
        r.setHum(Double.parseDouble(datosReg[6]));
        r.setTemp(Double.parseDouble(datosReg[7]));
        return r;
    }

    public ArrayList<Registro> leerDatGen(String ruta) {
        ArrayList<Registro> registros = new ArrayList<>(900);
        String linea = "";
        try {
            FileReader fileReader = new FileReader(new File(ruta));
            BufferedReader buffer = new BufferedReader(fileReader);
            while ((linea = buffer.readLine()) != null) {
                registros.add(crearRegistroF2(linea));
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

    private Registro crearRegistroF2(String linea) {
        String[] atributos = linea.split(",");
        Registro r = new Registro();
        r.setSector(atributos[0]);
        r.setIdSensor(Integer.parseInt(atributos[1]));
        r.setFecha(LocalDate.parse(atributos[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        r.setHora(LocalTime.parse(atributos[3]));
        r.setPm10(Math.floor((Double.parseDouble(atributos[4])) * 100) / 100);
        r.setPm25(Math.floor((Double.parseDouble(atributos[5])) * 100) / 100);
        r.setHum(Math.floor((Double.parseDouble(atributos[6])) * 100) / 100);
        r.setTemp(Math.floor((Double.parseDouble(atributos[7])) * 100) / 100);
        return r;
    }


}
