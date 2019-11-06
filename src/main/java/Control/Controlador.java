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
        return "ChartPage";
    }


    @RequestMapping("/treemap")
    public String mostrarTreemap(Model model) {
        //GestorRegistro gr = new GestorRegistro("src/archivos/csv.csv");
        //Treemap tree = new Treemap(gr.enviarDatosAlGrafico(gr.buscarDatosSolicitados("01-08-2019", "4:00:00 - 5:00:00")));
        List<DatoJSON> datos = new ArrayList<>();
        datos.add(new DatoJSON("Amanecer", 30));
        datos.add(new DatoJSON("labranza", 70));
        datos.add(new DatoJSON("centro", 100));
        Treemap tree = new Treemap(datos);
        model.addAttribute("data", tree);
        return "TreemapJSON";
    }


    @RequestMapping("/treemapJSON2")
    public String mostrarPaginaGraficoJSON(Model model) {
        return "TreemapJSON";
    }

}
