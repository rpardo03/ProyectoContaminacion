package Control;

import Modelo.Registro;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestorUpdate {

    public void actualizar(){
        escribirArchivo(agruparDia(ordenarRegistros(leerArchivo("src/archivos/csv.csv"))));
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
        ArrayList<Registro> igualFecha = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().getMonth().equals(registros.get(i + 1).getFecha().getMonth())) {
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

    private ArrayList<Registro> agruparAnio(ArrayList<Registro> registros) {
        Registro pivote = registros.get(0);
        ArrayList<Registro> igualFecha = new ArrayList<>();
        ArrayList<Registro> rPromedidados = new ArrayList<>();
        for (int i = 0; i < registros.size() - 1; i++) {
            if (pivote.getSector().equals(registros.get(i + 1).getSector()) && pivote.getFecha().getYear()==(registros.get(i + 1).getFecha().getYear())) {
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

    private ArrayList<Registro> leerArchivo(String ruta) {
        ArrayList<Registro> registros = new ArrayList<>(289000);
        String linea = "";
        int numLineas = 0;
        try {
            FileReader fileReader = new FileReader(new File(ruta));
            BufferedReader buffer = new BufferedReader(fileReader);
            while ((linea = buffer.readLine()) != null) {
                numLineas++;
                if (numLineas > 1) {
                    registros.add(crearRegistro(linea));
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

    private void escribirArchivo(ArrayList<Registro> registros) {
        File archivo = new File("src/archivos/DatosPorDia.csv");
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

    private Registro crearRegistro(String linea) {
        String[] datosReg = linea.split(",");
        Registro r = new Registro();
        r.setSector(datosReg[0]);
        r.setIdSensor(Integer.parseInt(datosReg[1]));
        r.setFecha(LocalDate.parse(datosReg[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        r.setHora(LocalTime.parse(datosReg[3],DateTimeFormatter.ofPattern("H:mm:ss")));
        r.setPm10(Double.parseDouble(datosReg[4]));
        r.setPm25(Double.parseDouble(datosReg[5]));
        r.setHum(Double.parseDouble(datosReg[6]));
        r.setTemp(Double.parseDouble(datosReg[7]));
        return r;
    }

    private ArrayList<Registro> ordenarRegistros(ArrayList<Registro> registros) {
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

}
