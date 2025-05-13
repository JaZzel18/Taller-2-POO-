import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa a un Administrador en el sistema TaskBee.
 * Un administrador puede registrar usuarios subordinados y gestionar sus tareas.
 */
public class Administrador extends Usuario {
    private  List<Usuario> usuarios;

    /**
     * Constructor del Administrador.
     *
     * @param nombreUsuario nombre de usuario del admin
     * @param contrasena contraseña del admin
     * @param correo correo electrónico del admin
     */
    public Administrador(String nombreUsuario, String contrasena, String correo) {
        super(nombreUsuario, contrasena, correo);
        this.usuarios = new ArrayList<>();
    }

    // Métodos para registrar usuarios y administrar tareas


    /**
     * Registra un nuevo usuario subordinado a este administrador.
     * Solicita datos por consola y genera una contraseña aleatoria.
     */
    public Usuario registrarUsuario() {

        Scanner sc = new Scanner(System.in);
        System.out.println("REGISTRO DE NUEVO USUARIO");
        System.out.println("Ingrese nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.println("Ingrese correo electronico: ");
        String correo = sc.nextLine();
        String contrasena = Sistema.crearContrasena(5); //metodo presente en la interface Sistema
        System.out.println("Contraseña para el nuevo usuario: " + contrasena);

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, correo);
        usuarios.add(nuevoUsuario);

        System.out.println("usuario registrado exitosamente");
        return nuevoUsuario;

    }
    
    /**
     * Agrega una nueva tarea a un usuario subordinado identificado por su correo.
     *
     * @param correo correo del usuario al que se le asignará la tarea
     * @param tarea tarea a agregar
     */
    public void agregarTareaUsuario(String correo, Tarea tarea) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                usuario.agregarTarea(tarea);
                System.out.println("Tarea agregada exitosamente a " + correo);
                return;
            }
        }
        System.out.println("No se encontró un usuario con el correo: " + correo);
    }

    /**
     * Elimina una tarea de un usuario subordinado, identificando por correo y título de la tarea.
     *
     * @param correo correo del usuario subordinado
     * @param tituloTitulo título de la tarea a eliminar
     */
    public void eliminarTareaUsuario(String correo, String tituloTitulo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                List<Tarea> tareas = new ArrayList<>(usuario.getTareas()); // copia para evitar ConcurrentModification
                for (Tarea t : tareas) {
                    if (t.getTitulo().equalsIgnoreCase(tituloTitulo)) {
                        usuario.getTareas().remove(t);
                        System.out.println("Tarea eliminada exitosamente de " + correo);
                        return;
                    }
                }
                System.out.println("No se encontró una tarea con el título: " + tituloTitulo);
                return;
            }
        }
        System.out.println("No se encontró un usuario con el correo: " + correo);
    }

    /**
     * Metodo que devuelve la lista de usuarios asociados a este administrador.
     *
     * @return la lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}

