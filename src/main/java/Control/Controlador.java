package Control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class Controlador {

    @RequestMapping("/home")
    public String principal() {
        return "PaginaPrincipal";
    }

    @RequestMapping("/ChartPage")
    public String mostrarPaginaGrafico() {
        return "ChartPage";
    }

}
