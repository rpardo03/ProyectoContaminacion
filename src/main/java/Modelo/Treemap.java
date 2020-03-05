package Modelo;

import java.util.List;

public class Treemap {

    private List<Registro> children;

    public Treemap(List<Registro> registros) {
        this.children = registros;
    }

    public List<Registro> getChildren() {
        return children;
    }

    public void setChildren(List<Registro> children) {
        this.children = children;
    }
}


