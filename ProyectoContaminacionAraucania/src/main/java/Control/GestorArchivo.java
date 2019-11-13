package Control;

import Modelo.Registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivo {

    public ArrayList<Registro> agruparRegistrosPorHora(ArrayList<Registro> registrosArchivo) {
        ArrayList<Registro> registros = new ArrayList<Registro>();
        ArrayList<Registro> registrosMismaFecha = new ArrayList<Registro>();
        String fechaAux = registrosArchivo.get(0).getFecha();
        for (int i = 0; i < registrosArchivo.size(); i++) {
            if (fechaAux.equals(registrosArchivo.get(i).getFecha())) {
                registrosMismaFecha.add(registrosArchivo.get(i));
                if (i == (registrosArchivo.size() - 1)) {
                    registros.addAll(crearRegistrosPorFecha(registrosMismaFecha));
                }
            } else {
                registros.addAll(crearRegistrosPorFecha(registrosMismaFecha));
                fechaAux = registrosArchivo.get(i).getFecha();
                registrosMismaFecha.clear();
                registrosMismaFecha.add(registrosArchivo.get(i));
            }
        }
        return registros;
    }

    private ArrayList<Registro> crearRegistrosPorFecha(ArrayList<Registro> registrosMismaFecha) {
        ArrayList<Registro> rMismaHora = new ArrayList<Registro>();
        ArrayList<Registro> rPromediados = new ArrayList<Registro>();
        String horaAux = desglosarHora(registrosMismaFecha.get(0).getHora());
        for (int i = 0; i < registrosMismaFecha.size(); i++) {
            if (horaAux.equals(desglosarHora(registrosMismaFecha.get(i).getHora()))) {
                rMismaHora.add(registrosMismaFecha.get(i));
                if (i == (registrosMismaFecha.size() - 1)) {
                    rPromediados.add(crearRegistroPromediado(rMismaHora));
                }
            } else {
                rPromediados.add(crearRegistroPromediado(rMismaHora));
                horaAux = desglosarHora(registrosMismaFecha.get(i).getHora());
                rMismaHora.clear();
                rMismaHora.add(registrosMismaFecha.get(i));
                if (i == (registrosMismaFecha.size() - 1)) {
                    rPromediados.add(crearRegistroPromediado(rMismaHora));
                }
            }
        }
        return rPromediados;
    }

    private String desglosarHora(String hora) {
        String[] datoHorario = hora.split(":");
        return datoHorario[0];
    }

    private Registro crearRegistroPromediado(ArrayList<Registro> rMismaHora) {
        Registro r = new Registro();
        r.setSector(rMismaHora.get(0).getSector());
        r.setIdSensor(rMismaHora.get(0).getIdSensor());
        r.setFecha(rMismaHora.get(0).getFecha());
        r.setHora(establecerRangoHora(rMismaHora));
        r.setPm10(promediarPM10(rMismaHora));
        r.setPm25(promediarPM25(rMismaHora));
        r.setHumedad(promediarHumedad(rMismaHora));
        r.setTemperatura(promediarTemperatura(rMismaHora));
        return r;
    }

    private String establecerRangoHora(ArrayList<Registro> rMismaHora) {
        String rangoHora = "";
        int hora = Integer.parseInt(desglosarHora(rMismaHora.get(0).getHora()));
        if (hora == 23) {
            rangoHora = hora + ":00:00" + " - " + "00:00:00";
        } else {
            rangoHora = hora + ":00:00" + " - " + (hora + 1) + ":00:00";
        }
        return rangoHora;
    }

    private double promediarPM10(ArrayList<Registro> rMismaHora) {
        double sumaPM10 = 0.0;
        for (int i = 0; i < rMismaHora.size(); i++) {
            sumaPM10 += rMismaHora.get(i).getPm10();
        }
        return sumaPM10 / rMismaHora.size();
    }

    private double promediarPM25(ArrayList<Registro> rMismaHora) {
        double sumaPM25 = 0.0;
        for (int i = 0; i < rMismaHora.size(); i++) {
            sumaPM25 += rMismaHora.get(i).getPm25();
        }
        return sumaPM25 / rMismaHora.size();
    }

    private double promediarTemperatura(ArrayList<Registro> rMismaHora) {
        double sumaTemp = 0.0;
        for (int i = 0; i < rMismaHora.size(); i++) {
            sumaTemp = sumaTemp + rMismaHora.get(i).getTemperatura();
        }
        return (sumaTemp / rMismaHora.size());
    }

    private double promediarHumedad(ArrayList<Registro> rMismaHora) {
        double sumaHum = 0.0;
        for (int i = 0; i < rMismaHora.size(); i++) {
            sumaHum = sumaHum + rMismaHora.get(i).getHumedad();
        }
        return sumaHum / rMismaHora.size();
    }

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
