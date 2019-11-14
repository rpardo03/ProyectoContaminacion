import Control.GestorRegistro;
import Modelo.Registro;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertFalse;


public class GestorRegistroTest {
    private ArrayList<Registro> registros;
    public GestorRegistro gesRegistro;


    @Before
    public void setUp() throws Exception {
        gesRegistro = new GestorRegistro();
        registros = new ArrayList<>();
        registros.add(new Registro("Amanecer", 1, "30-04-2019", "22:39:59", 134.0, 132.0, 30.5, 23));
        registros.add(new Registro("Centro", 1, "01-05-2019", "03:15:59", 123.0, 135.0, 34.3, 40));
        registros.add(new Registro("Amanecer", 1, "30-04-2019", "20:30:59", 110.0, 115.0, 31.4, 80));
        registros.add(new Registro("Universidad", 1, "02-04-2019", "14:24:12", 123.6, 115.5, 20.4, 30));

    }

    /* Test que prueba la busqueda de archivos que no están almacenados*/
    @Test
    public void testBusquedaDatos() {
        assertFalse("los datos no se encuentran", gesRegistro.buscarDatos("30-04-2019", "22:00:00",registros));
    }
}
