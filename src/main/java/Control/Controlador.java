package Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class Controlador {

    public void agregarRegistrosBD() {
        // TODO - implement Controlador.agregarRegistrosBD
        throw new UnsupportedOperationException();
    }

    @RequestMapping()
    public String principal(Model model){
        return "PaginaPrincipal";
    }


    public void mostrarPaginaGrafico() {
        // TODO - implement Controlador.mostrarPaginaGrafico
        throw new UnsupportedOperationException();
    }

    public void cargarDatosGrafico(double pm10,double pm25,double temperatura,String sector,double humedad) {
        //aca le pasan los datos al grafico con los parametros
    }

}
