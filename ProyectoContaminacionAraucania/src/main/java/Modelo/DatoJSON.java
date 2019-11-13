package Modelo;


public class DatoJSON {

    private String name;
    private Integer value;

    public DatoJSON(String sector, Integer pm25) {
        this.name = sector;
        this.value = pm25;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
