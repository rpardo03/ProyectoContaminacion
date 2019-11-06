package Modelo;

import java.util.List;

public class Treemap {

    private List<DatoJSON> registros;

    public Treemap(List<DatoJSON> registros){
        this.registros=registros;
    }

    public List<DatoJSON> getRegistros() {
        return registros;
    }

    public void setRegistros(List<DatoJSON> registros) {
        this.registros = registros;
    }

}
