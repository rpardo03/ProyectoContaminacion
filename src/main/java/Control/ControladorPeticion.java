package Control;

import Modelo.Registro;
import Modelo.Treemap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ControladorPeticion {

    private GestorRegistro gr = new GestorRegistro();

    @RequestMapping(value = "/obtenerDatosHora", method = RequestMethod.GET)
    public Treemap sendDataHour(@RequestParam(name = "fecha", required = true) String fecha, @RequestParam(name = "hora", required = true) String hora) {
        List<Registro> datos = gr.searchHour(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("MM/dd/yyyy")),LocalTime.parse(hora));
        return new Treemap(datos);
    }

    @RequestMapping(value = "/obtenerDatosDia", method = RequestMethod.GET)
    public Treemap sendData(@RequestParam(name = "fecha", required = true, defaultValue = "09/08/2019") String fecha) {
        List<Registro> datos = gr.buscarRegistros(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return new Treemap(datos);
    }

    @RequestMapping(value = "/obtenerDatosMes", method = RequestMethod.GET)
    public Treemap sendDataMonth(@RequestParam(name = "mes", required = true) String mes) {
        List<Registro> datos = gr.searchMonth(LocalDate.parse(mes, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return new Treemap(datos);
    }

    @RequestMapping(value = "/obtenerDatosAnio", method = RequestMethod.GET)
    public Treemap sendDataYear(@RequestParam(name = "anio", required = true) String anio) {
        List<Registro> datos = gr.searchYear(LocalDate.parse(anio, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return new Treemap(datos);
    }

}
