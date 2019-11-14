import Control.GestorArchivo;
import Modelo.Registro;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GestorArchivoTest {
   private GestorArchivo gestor;
   private ArrayList<Registro> registros;

    @Before
    public void setUp(){
        gestor = new GestorArchivo();
        registros = null;
    }

    @Test
    /*Test que comprueba una ruta invalida al leer un archivo.csv */
    public void testLectura() {
        assertNull(gestor.leerArchivo("archivo.csv"));
    }

    @Test
   /* Test que prueba el promedio de humedad de un registro de largo 0*/
    public void testPromedioHumedad(){
        double esperado = 0.0;
        double resultado = gestor.promediarHumedad(registros);
        assertEquals(esperado,resultado,0.001);
    }

    @Test
    /*Test que prueba el ingreso de un registro vacio */
    public void testRegistro(){
        Registro obtenido = gestor.crearRegistro("");
        assertNull(obtenido);
    }



}
