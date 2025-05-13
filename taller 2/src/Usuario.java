import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario del sistema TaskBee.
 * Cada usuario tiene un nombre de usuario, contrasena, correo y una lista de tareas asociadas.
 */
public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private List<Tarea> tareas;

    /**
     * Constructor de la clase Usuario
     * Posee la información básica y una lista vacía de tareas.
     *
     * @param nombreUsuario nombre del usuario
     * @param contrasena contraseña del usuario
     * @param correo correo electrónico del usuario
     */
    public Usuario(String nombreUsuario, String contrasena, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.tareas = new ArrayList<Tarea>();

    }

    /**
     * Metodo que agrega una nueva tarea a la lista del usuario.
     *
     * @param tarea una tarea a agregar a la lista del usuario
     */
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    /**
     * Metodo que muestra todas las tareas del usuario por consola.
     */
    public void verTareas() {
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }
    /**
     * Metodo que devuelve la lista de tareas asociadas a este usuario.
     *
     * @return la lista de tareas asociadas al usuario
     */
    public List<Tarea> getTareas() {
        return tareas;
    }

    // Getters y Setters de la clase usuario.
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}




