package Control;

import Modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorRegistro {

    private ArrayList<Registro> regHora;
    private ArrayList<Registro> regDia;
    private ArrayList<Registro> regMonth;
    private ArrayList<Registro> regYear;

    public GestorRegistro() {
        agregarRegistros();
    }

    private void agregarRegistros() { // agregar los archivos ordenándolos por fecha.
        GestorArchivo ga = new GestorArchivo();
        GestorUpdate gu = new GestorUpdate();
        this.regHora = gu.ordenarRegistros(ga.leerDatGen("src/archivos/DatosPorHora.csv"));
        this.regDia = gu.ordenarRegistros(ga.leerDatGen("src/archivos/DatosPorDia.csv"));
        this.regMonth = gu.ordenarRegistros(ga.leerDatGen("src/archivos/DatosPorMes.csv"));
        this.regYear = gu.ordenarRegistros(ga.leerDatGen("src/archivos/DatosPorAnio.csv"));
    }

    public ArrayList<Registro> buscarRegistros(LocalDate fecha) {
        ArrayList<Registro> encontrados = new ArrayList<>();
        int high = this.regDia.size() - 1;
        int low = 0;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (regDia.get(mid).getFecha().compareTo(fecha) < 0) {
                high = mid - 1;
            } else if (regDia.get(mid).getFecha().compareTo(fecha) > 0) {
                low = mid + 1;
            } else {
                while ((mid > 0) && regDia.get(mid - 1).getFecha().equals(fecha)) {
                    mid--;
                }
                while (mid < regDia.size() && regDia.get(mid).getFecha().equals(fecha)) {
                    encontrados.add(regDia.get(mid));
                    mid++;
                }
                return encontrados;
            }
        }
        return null;
    }

    public ArrayList<Registro> searchHour(LocalDate fecha, LocalTime hora) {
        ArrayList<Registro> encontrados = new ArrayList<>();
        int high = this.regHora.size() - 1;
        int low = 0;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (regHora.get(mid).getFecha().compareTo(fecha) < 0) {
                high = mid - 1;
            } else if (regHora.get(mid).getFecha().compareTo(fecha) > 0) {
                low = mid + 1;
            } else {
                while ((mid > 0) && regHora.get(mid - 1).getFecha().equals(fecha)) {
                    mid--;
                }
                while (mid < regHora.size() && regHora.get(mid).getFecha().equals(fecha)) {
                    if (regHora.get(mid).getHora().getHour() == hora.getHour()) {
                        encontrados.add(regHora.get(mid));
                    }
                    mid++;
                }
                return encontrados;
            }
        }
        return null;
    }

    public ArrayList<Registro> searchMonth(LocalDate fecha) {
        ArrayList<Registro> encontrados = new ArrayList<>();
        for (int i = 0; i < regMonth.size(); i++) {
            if (regMonth.get(i).getFecha().getYear() == fecha.getYear() && regMonth.get(i).getFecha().getMonth().equals(fecha.getMonth())) {
                encontrados.add(regMonth.get(i));
            }
        }
        return encontrados;
    }

    public ArrayList<Registro> searchYear(LocalDate fecha) {
        ArrayList<Registro> encontrados = new ArrayList<>();
        for (int i = 0; i < regYear.size(); i++) {
            if (regYear.get(i).getFecha().getYear() == fecha.getYear()) {
                encontrados.add(regYear.get(i));
            }
        }
        return encontrados;
    }

    public ArrayList<Registro> getRegMonth() {
        return regMonth;
    }

    public void setRegMonth(ArrayList<Registro> regMonth) {
        this.regMonth = regMonth;
    }

    public ArrayList<Registro> getRegYear() {
        return regYear;
    }

    public void setRegYear(ArrayList<Registro> regYear) {
        this.regYear = regYear;
    }

    public ArrayList<Registro> getRegistros() {
        return regDia;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.regDia = registros;
    }
}
