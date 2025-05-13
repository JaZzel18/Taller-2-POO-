/**
 * Clase principal Main.
 * Ocupada de iniciar el Sistema Taskbee.
 *
 * @author Emerson Tacanahui
 * @author  Gonzalo Marchant
 */
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new SistemaImpl();
        sistema.mostrarMenu();
    }
}