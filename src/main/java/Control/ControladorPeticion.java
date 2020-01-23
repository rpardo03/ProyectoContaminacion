package Control;

import Modelo.Registro;
import Modelo.Treemap;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ControladorPeticion {

    private GestorRegistro gr = new GestorRegistro();

    @RequestMapping(value = "/obtenerDatos",method = RequestMethod.GET)
    public Treemap sendData(@RequestParam(name = "fecha",required = true,defaultValue = "09/08/2019") String fecha ){
        List<Registro> datos = gr.buscarRegistros(LocalDate.parse(fecha,DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return new Treemap(datos);
    }

}
