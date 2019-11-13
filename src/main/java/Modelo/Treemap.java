package Modelo;

import java.util.List;

public class Treemap {

    private List<DatoJSON> children;

    public Treemap(List<DatoJSON> registros) {
        this.children = registros;
    }

    public List<DatoJSON> getChildren() {
        return children;
    }

    public void setChildren(List<DatoJSON> children) {
        this.children = children;
    }
}


