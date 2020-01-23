import Control.Controlador;
import Control.GestorUpdate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = Controlador.class)
public class Main {

    public static void main(String[] args) {
       //actualizarNuevosDatos();
        SpringApplication.run(Main.class, args);
    }

    private static void actualizarNuevosDatos(){
        GestorUpdate ga = new GestorUpdate();
        ga.actualizar();
    }

}
