package Control;

import Modelo.Registro;
import java.util.ArrayList;

public class GestorUpdate {

    public void actualizar() {
        GestorArchivo ga = new GestorArchivo();
        ga.escribirArchivo(agruparHora(ordenarAlf(ordenarRegistros(ga.leerDatOrig("src/archivos/csv.csv")))), "src/archivos/DatosPorHora.csv");
        ga.escribirArchivo(agruparDia(ordenarAlf(ordenarRegistros(ga.leerDatOrig("src/archivos/csv.csv")))), "src/archivos/DatosPorDia.csv");
        ga.escribirArchivo(agruparMes(ga.leerDatGen("src/archivos/DatosPorDia.csv")), "src/archivos/DatosPorMes.csv");
        ga.escribirArchivo(agruparAnio(ga.leerDatGen("src/archivos/DatosPorMes.csv")), "src/archivos/DatosPorAnio.csv");
    }

    private ArrayList<Registro> agruparHora(ArrayList<Registro> registros) {
        Registro pivote = registros.get(0);
        ArrayList<Registro> igualFecha = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().equals(registros.get(i + 1).getFecha()) && pivote.getHora().getHour() == registros.get(i).getHora().getHour()) {
                igualFecha.add(registros.get(i));
            } else {
                if (igualFecha.size() == 0) {
                    igualFecha.add(registros.get(i));
                }
                rPromedidados.add(promediar(igualFecha));
                igualFecha.clear();
                pivote = registros.get(i + 1);
            }
        }
        rPromedidados.add(promediar(igualFecha));
        return rPromedidados;
    }

    private ArrayList<Registro> agruparDia(ArrayList<Registro> registros) {
        Registro pivote = registros.get(0);
        ArrayList<Registro> igualFecha = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().equals(registros.get(i + 1).getFecha())) {
                igualFecha.add(registros.get(i));
            } else {
                if (igualFecha.size() == 0) {
                    igualFecha.add(registros.get(i));
                }
                rPromedidados.add(promediar(igualFecha));
                igualFecha.clear();
                pivote = registros.get(i + 1);
            }
        }
        rPromedidados.add(promediar(igualFecha));
        return rPromedidados;
    }

    private ArrayList<Registro> agruparMes(ArrayList<Registro> registros) {
        Registro pivote = registros.get(0);
        ArrayList<Registro> igualMes = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().getMonth().equals(registros.get(i + 1).getFecha().getMonth())) {
                igualMes.add(registros.get(i));
            } else {
                if (igualMes.size() == 0) { // si el registro es único.
                    igualMes.add(registros.get(i));
                }
                rPromedidados.add(promediar(igualMes));
                igualMes.clear();
                pivote = registros.get(i + 1);
            }
        }
        return rPromedidados;
    }

    private ArrayList<Registro> agruparAnio(ArrayList<Registro> registros) {
        Registro pivote = registros.get(0);
        ArrayList<Registro> igualFecha = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().getYear() == (registros.get(i + 1).getFecha().getYear())) {
                igualFecha.add(registros.get(i));
            } else {
                if (igualFecha.size() == 0) {
                    igualFecha.add(registros.get(i));
                }
                rPromedidados.add(promediar(igualFecha));
                igualFecha.clear();
                pivote = registros.get(i + 1);
            }
        }
        return rPromedidados;
    }

    private Registro promediar(ArrayList<Registro> igualFecha) {
        igualFecha.get(0).setPm10(promediarPM10(igualFecha));
        igualFecha.get(0).setPm25(promediarPM25(igualFecha));
        igualFecha.get(0).setHum(promediarHumedad(igualFecha));
        igualFecha.get(0).setTemp(promediarTemperatura(igualFecha));
        return igualFecha.get(0);
    }

    private double promediarPM10(ArrayList<Registro> igualFecha) {
        double sumaPM10 = 0.0;
        for (int i = 0; i < igualFecha.size(); i++) {
            sumaPM10 += igualFecha.get(i).getPm10();
        }
        return sumaPM10 / igualFecha.size();
    }

    private double promediarPM25(ArrayList<Registro> igualFecha) {
        double sumaPM25 = 0.0;
        for (int i = 0; i < igualFecha.size(); i++) {
            sumaPM25 += igualFecha.get(i).getPm25();
        }
        return sumaPM25 / igualFecha.size();
    }

    private double promediarTemperatura(ArrayList<Registro> igualFecha) {
        double sumaTemp = 0.0;
        for (int i = 0; i < igualFecha.size(); i++) {
            sumaTemp = sumaTemp + igualFecha.get(i).getTemp();
        }
        return (sumaTemp / igualFecha.size());
    }

    private double promediarHumedad(ArrayList<Registro> igualFecha) {
        double sumaHum = 0.0;
        try {
            for (int i = 0; i < igualFecha.size(); i++) {
                sumaHum = sumaHum + igualFecha.get(i).getHum();
            }
            return sumaHum / igualFecha.size();
        } catch (Exception e) {
            System.out.println("No se puede dividir por cero");
            return 0.0;
        }
    }

    public ArrayList<Registro> ordenarRegistros(ArrayList<Registro> registros) {
        if (registros.size() > 1) {
            ArrayList<Registro> smaller = new ArrayList<>();
            ArrayList<Registro> same = new ArrayList<>();
            ArrayList<Registro> larger = new ArrayList<>();

            Registro pivot = registros.get(registros.size() / 2);

            for (Registro r : registros) {

                if (r.getFecha().compareTo(pivot.getFecha()) > 0) {
                    smaller.add(r);
                } else if (r.getFecha().compareTo(pivot.getFecha()) < 0) {
                    larger.add(r);
                } else {
                    same.add(r);
                }
            }

            ordenarRegistros(smaller);
            ordenarRegistros(larger);

            registros.clear();
            registros.addAll(smaller);
            registros.addAll(same);
            registros.addAll(larger);
        }
        return registros;
    }

    private ArrayList<Registro> ordenarAlf(ArrayList<Registro> registros) {
        if (registros.size() > 0) {
            ArrayList<Registro> larger = new ArrayList<>();
            ArrayList<Registro> smaller = new ArrayList<>();
            ArrayList<Registro> same = new ArrayList<>();

            Registro pivote = registros.get(registros.size() / 2);

            for (Registro r : registros) {
                if (r.getSector().compareTo(pivote.getSector()) < 0) {
                    smaller.add(r);
                } else if (r.getSector().compareTo(pivote.getSector()) > 0) {
                    larger.add(r);
                } else {
                    same.add(r);
                }
            }

            ordenarAlf(smaller);
            ordenarAlf(larger);

            registros.clear();
            registros.addAll(smaller);
            registros.addAll(same);
            registros.addAll(larger);
        }
        return registros;
    }

}
