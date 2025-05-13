import java.io.IOException;

public interface Sistema {

    /**
     * Lee los usuarios desde el archivo user.txt
     */
    void leerUsuariosDesdeArchivo(String rutaArchivo) throws IOException;

    /**
     * Lee las tareas desde el archivo tareas.txt
     */
    void leerTareasDesdeArchivo(String rutaArchivo) throws IOException;

    /**
     * Guarda los datos actuales de usuarios y tareas en sus archivos correspondientes.
     */
    void guardarDatos() throws IOException;

    /**
     * Inicia sesión con un nombre de usuario y contraseña.
     *
     * @param nombreUsuario nombre de usuario
     * @param contrasena contraseña del usuario
     * @return Usuario autenticado o null si falla
     */
    Usuario iniciarSesion(String nombreUsuario, String contrasena);

    /**
     * Registra un nuevo administrador.
     *
     * @param nombreUsuario nombre del administrador
     * @param correo correo del administrador
     */
    void registrarAdministrador(String nombreUsuario, String correo);

    /**
     * Muestra el menú principal del sistema.
     */
    void mostrarMenu();

    /**
     * Genera una contraseña aleatoria de una longitud determinada.
     *
     * @param longitud cantidad de caracteres
     * @return contraseña generada
     */
    static String crearContrasena(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int index = (int) (Math.random() * caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
}