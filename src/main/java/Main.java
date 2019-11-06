import Control.Controlador;
import Control.GestorRegistro;
import Modelo.DatoJSON;
import Modelo.Registro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackageClasses = Controlador.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
