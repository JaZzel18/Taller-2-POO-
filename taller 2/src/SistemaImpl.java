import java.io.*;
import java.util.*;

/**
 * Clase que implementa el sistema TaskBee.
 * Controla el ciclo completo: carga de datos, gestión de sesiones, menús y persistencia.
 */
public class SistemaImpl implements Sistema {

    private List<Usuario> usuarios;
    private List<Tarea> tareas;
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor: inicializa las listas de usuarios y tareas.
     */
    public SistemaImpl() {
        usuarios = new ArrayList<>();
        tareas = new ArrayList<>();
    }

    /**
     * Carga los usuarios desde el archivo "usuarios.txt".
     */
    @Override
    public void leerUsuariosDesdeArchivo(String rutaArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombre = partes[0];
            String contrasena = partes[1];
            String correo = partes[2];

            Usuario usuario = new Usuario(nombre, contrasena, correo);
            usuarios.add(usuario);
        }
        br.close();
    }

    /**
     * Carga las tareas desde el archivo "tareas.txt" y las asigna al usuario correspondiente.
     */
    @Override
    public void leerTareasDesdeArchivo(String rutaArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String titulo = partes[0];
            String descripcion = partes[1];
            String fechaCreada = partes[2];
            String fechaLimite = partes[3];
            boolean estado = Boolean.parseBoolean(partes[4]);
            String correo = partes[5];

            Tarea tarea = new Tarea(titulo, descripcion, fechaCreada, fechaLimite, estado, correo);
            tareas.add(tarea);

            for (Usuario u : usuarios) {
                if (u.getCorreo().equalsIgnoreCase(correo)) {
                    u.agregarTarea(tarea);
                    break;
                }
            }
        }
        br.close();
    }

    /**
     * Guarda todos los usuarios en "usuarios.txt".
     */
    @Override
    public void guardarDatos() throws IOException {
        BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter("usuarios.txt"));
        for (Usuario u : usuarios) {
            bwUsuarios.write(u.getNombreUsuario() + "," + u.getContrasena() + "," + u.getCorreo());
            bwUsuarios.newLine();
        }
        bwUsuarios.close();

        BufferedWriter bwTareas = new BufferedWriter(new FileWriter("tareas.txt"));
        for (Tarea t : tareas) {
            bwTareas.write(t.getTitulo() + "," + t.getDescripcion() + "," + t.getFechaCreada() + "," +
                    t.getFechaLimite() + "," + t.getEstado() + "," + t.getCorreoAsignado());
            bwTareas.newLine();
        }
        bwTareas.close();
    }

    /**
     * Inicia sesión con las credenciales del usuario.
     *
     * @return usuario autenticado o null
     */
    @Override
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario) && u.getContrasena().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Registra un nuevo administrador y lo agrega a la lista.
     */
    @Override
    public void registrarAdministrador(String nombreUsuario, String correo) {
        String contrasena = Sistema.crearContrasena(5);
        Administrador admin = new Administrador(nombreUsuario, contrasena, correo);
        usuarios.add(admin);
        System.out.println("Administrador registrado. Contraseña: " + contrasena);
    }

    /**
     * Muestra el menú principal del sistema y dirige la navegación del usuario.
     */
    @Override
    public void mostrarMenu() {
        try {
            leerUsuariosDesdeArchivo("src/user.txt");
            leerTareasDesdeArchivo("src/tareas.txt");
        } catch (IOException e) {
            System.out.println("Error cargando archivos: " + e.getMessage());
        }

        boolean activo = true;
        while (activo) {
            System.out.println("______ BIENVENIDO A TASKBEE ______");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar administrador");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();
                    Usuario sesion = iniciarSesion(user, pass);
                    if (sesion != null) {
                        if (sesion instanceof Administrador) {
                            menuAdmin((Administrador) sesion);
                        } else {
                            menuUsuario(sesion);
                        }
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                    break;
                case "2":
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    registrarAdministrador(nombre, correo);
                    break;
                case "3":
                    try {
                        guardarDatos();
                    } catch (IOException e) {
                        System.out.println("Error al guardar archivos: " + e.getMessage());
                    }
                    System.out.println("Saliendo del sistema...");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra el menú de usuario común.
     * Permite agregar tareas, revisar tareas y cerrar sesión.
     *
     * @param usuario usuario autenticado
     */
    public void menuUsuario(Usuario usuario) {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- MENÚ USUARIO ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Revisar tareas");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Fecha de creación: ");
                    String fechaCreacion = sc.nextLine();
                    System.out.print("Fecha límite: ");
                    String fechaLimite = sc.nextLine();
                    boolean estado = false; // La tarea se crea incompleta

                    Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaCreacion, fechaLimite, estado, usuario.getCorreo());
                    usuario.agregarTarea(nuevaTarea);
                    tareas.add(nuevaTarea);

                    System.out.println("Tarea agregada correctamente.");
                    break;
                case "2":
                    usuario.verTareas();
                    break;
                case "3":
                    System.out.println("Cerrando sesión...");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    /**
     * Muestra el menú del administrador.
     * Permite registrar usuarios, gestionar tareas de subordinados y cerrar sesión.
     *
     * @param admin administrador autenticado
     */
    public void menuAdmin(Administrador admin) {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Agregar tarea a subordinado");
            System.out.println("3. Eliminar tarea de subordinado");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    // Registro directo del subordinado
                    Usuario nuevoUsuario = admin.registrarUsuario(); // debes hacer que retorne el nuevo usuario
                    usuarios.add(nuevoUsuario); // agregamos a la lista global también
                    break;
                case "2":
                    System.out.print("Correo del usuario subordinado: ");
                    String correoAgregar = sc.nextLine();

                    if (admin.getUsuarios().stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correoAgregar))) {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = sc.nextLine();
                        System.out.print("Fecha de creación: ");
                        String fechaCreacion = sc.nextLine();
                        System.out.print("Fecha límite: ");
                        String fechaLimite = sc.nextLine();
                        boolean estado = false;

                        Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaCreacion, fechaLimite, estado, correoAgregar);
                        admin.agregarTareaUsuario(correoAgregar, nuevaTarea);
                        tareas.add(nuevaTarea);
                        System.out.println("Tarea asignada correctamente.");
                    } else {
                        System.out.println("Este usuario no está bajo tu supervisión.");
                    }
                    break;

                case "3":
                    System.out.print("Correo del usuario subordinado: ");
                    String correoEliminar = sc.nextLine();

                    if (admin.getUsuarios().stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correoEliminar))) {
                        System.out.print("Título de la tarea a eliminar: ");
                        String tituloEliminar = sc.nextLine();
                        admin.eliminarTareaUsuario(correoEliminar, tituloEliminar);
                        tareas.removeIf(t -> t.getCorreoAsignado().equalsIgnoreCase(correoEliminar)
                                && t.getTitulo().equalsIgnoreCase(tituloEliminar));
                    } else {
                        System.out.println("Este usuario no está bajo tu supervisión.");
                    }
                    break;
                case "4":
                    System.out.println("Cerrando sesión...");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}