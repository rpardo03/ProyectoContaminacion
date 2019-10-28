package Control;

import Modelo.Registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivo {

    public ArrayList<Registro> leerArchivo(String ruta) {
        ArrayList<Registro> registros = new ArrayList<Registro>();
        String linea = "";
        int numLineas = 0;
        try {
            FileReader fileReader = new FileReader(new File(ruta));
            BufferedReader buffer = new BufferedReader(fileReader);
            while ((linea = buffer.readLine()) != null) {
                numLineas++;
                if (numLineas > 1) {
                    registros.add(crearRegistro(linea + ";"));
                }
            }
            buffer.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado.");
        }
        return registros;
    }

    private Registro crearRegistro(String linea) {
        String datos[] = arreglarLinea(linea).split(";");
        return new Registro(datos[0], Integer.parseInt(datos[1]), datos[2], datos[3], Double.parseDouble(datos[4]), Double.parseDouble(datos[5]), Double.parseDouble(datos[7]), Double.parseDouble(datos[6]));
    }

    private String arreglarLinea(String linea) {
        while (linea.contains(";;")) {
            linea = linea.replaceAll("(;;)", ";0.0;");
        }
        return linea;
    }
}
