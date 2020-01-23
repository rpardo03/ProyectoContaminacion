package Control;

import Modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorRegistro {

    private ArrayList<Registro> registros;

    public GestorRegistro() {
        this.registros = agregarRegistros();
    }

    private ArrayList<Registro> agregarRegistros() {
        GestorArchivo ga = new GestorArchivo();
        return ga.leerDatos("src/archivos/DatosPorDia.csv");
    }

    public ArrayList<Registro> buscarRegistros(LocalDate fecha) {
        ArrayList<Registro> encontrados = new ArrayList<>();
        int high = this.registros.size() - 1;
        int low = 0;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (registros.get(mid).getFecha().compareTo(fecha) < 0) {
                high = mid - 1;
            } else if (registros.get(mid).getFecha().compareTo(fecha) > 0) {
                low = mid + 1;
            } else {
                while ((mid > 0) && registros.get(mid - 1).getFecha().equals(fecha)) {
                    mid--;
                }
                while (mid < registros.size() && registros.get(mid).getFecha().equals(fecha)) {
                    encontrados.add(registros.get(mid));
                    mid++;
                }
                return encontrados;
            }
        }
        return null;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }
}
