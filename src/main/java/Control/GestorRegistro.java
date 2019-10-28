package Control;

import Modelo.*;

import java.util.ArrayList;

public class GestorRegistro {

    BaseDatoSQL BaseDatos;

    public GestorRegistro() {

    }

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
        r.setHora(desglosarHora(rMismaHora.get(0).getHora()) + ":00:00");
        r.setPm10(promediarPM10(rMismaHora));
        r.setPm25(promediarPM25(rMismaHora));
        r.setTemperatura(promediarTemperatura(rMismaHora));
        return r;
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

    public void ordenarRegistros() {
        // TODO - implement GestorRegistro.ordenarRegistros
        throw new UnsupportedOperationException();
    }

    public void enviarDatosAlGrafico() {
        // TODO - implement GestorRegistro.enviarDatosAlGrafico
        throw new UnsupportedOperationException();
    }

}
