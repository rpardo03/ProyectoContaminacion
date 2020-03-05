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

    //Ejecutar este método solo cuando haya un nuevo archivo con nuevos datos.
    private static void actualizarNuevosDatos(){
        GestorUpdate ga = new GestorUpdate();
        ga.actualizar();
    }

}
