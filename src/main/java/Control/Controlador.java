package Control;

import Modelo.DatoJSON;
import Modelo.Treemap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class Controlador {

    @RequestMapping("/home")
    public String principal(Model model) {
        return "PaginaPrincipal";
    }

    @RequestMapping("/ChartPage")
    public String mostrarPaginaGrafico(Model model) {
        GestorRegistro gr = new GestorRegistro("src/archivos/csv.csv");
        List<DatoJSON> datos = gr.enviarDatosAlGrafico(gr.buscarDatosSolicitados("30-04-2019", "16:00:00 - 17:00:00"));
        Treemap tree = new Treemap(datos);
        model.addAttribute("data", tree);
        return "ChartPage";
    }


}
