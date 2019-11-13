package Control;

import Modelo.*;

import java.util.ArrayList;
import java.util.List;

public class GestorRegistro {

    private ArrayList<Registro> registros;

    public GestorRegistro(String ruta) {
        this.registros = agregarRegistrosNuevos(ruta);
    }

    private ArrayList<Registro> agregarRegistrosNuevos(String ruta) {
        GestorArchivo ga = new GestorArchivo();
        return ga.agruparRegistrosPorHora(ga.leerArchivo(ruta));
    }

    public ArrayList<Registro> buscarDatosSolicitados(String fecha, String rangoHorario) {
        ArrayList<Registro> datosSolicitados = new ArrayList<>();
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getFecha().equals(fecha) && registros.get(i).getHora().equals(rangoHorario)) {
                datosSolicitados.add(registros.get(i));
            }
        }
        return datosSolicitados;
    }


    public List<DatoJSON> enviarDatosAlGrafico(ArrayList<Registro> datosParaEnviar) {
        List<DatoJSON> enviar = new ArrayList<>();
        for (int i = 0; i < datosParaEnviar.size(); i++) {
            enviar.add(new DatoJSON(datosParaEnviar.get(i).getSector(), (int) datosParaEnviar.get(i).getPm25()));
        }
        return enviar;
    }


    //aun no se usa
    private ArrayList<Registro> promediarDatosSolicitados() {
        ArrayList<Registro> datosSolicitados = buscarDatosSolicitados("", "");
        ArrayList<Registro> datosMismoSector = new ArrayList<>();
        String sector = datosSolicitados.get(0).getSector();
        for (int i = 0; i < datosSolicitados.size(); i++) {
            if (datosSolicitados.get(i).getSector().equals(sector)) {
                datosMismoSector.add(datosSolicitados.get(i));
            } else {

            }
        }
        return datosMismoSector;
    }

    //aun no se usa
    private Registro promediarPM(ArrayList<Registro> datos) {
        double promedio = 0.0;
        for (int i = 0; i < datos.size(); i++) {
            promedio += datos.get(i).getPm25();
        }
        return new Registro();
    }


    private ArrayList<Registro> ordenarRegistros() {
        // TODO - implement GestorRegistro.ordenarRegistros
        throw new UnsupportedOperationException();
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }
}
