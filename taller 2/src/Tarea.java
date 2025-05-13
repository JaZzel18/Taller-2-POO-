/**
 * Clase que representa una tarea del sistema TaskBee.
 * Una tarea contiene información como título, descripción, fechas, estado y el usuario asociado.
 */
public class Tarea {
    private String titulo;
    private String descripcion;
    private String fechaCreada;
    private String fechaLimite;
    private boolean estado;
    private String correoAsignado;

    /**
     * Constructor de la clase tarea.
     * Guarda un titulo, una descripcion, una fecha de creacion y una de limite, si esta o no la tarea completa y el correo al cual esta asignada la tarea.
     *
     * @param titulo título de la tarea
     * @param descripcion descripción detallada
     * @param fechaCreada fecha en que se creó la tarea
     * @param fechaLimite fecha límite para completarla
     * @param estado true si está completada, false si no
     * @param correoAsignado correo del usuario al que pertenece la tarea
     */
    public Tarea(String titulo, String descripcion, String fechaCreada, String fechaLimite, boolean estado, String correoAsignado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreada = fechaCreada;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.correoAsignado = correoAsignado;
    }


    // Getters y  Setters de la clase Tarea
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFechaCreada() {
        return fechaCreada;
    }
    public void setFechaCreada(String fechaCreada) {
        this.fechaCreada = fechaCreada;
    }
    public String getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getCorreoAsignado() {
        return correoAsignado;
    }
    public void setCorreoAsignado(String correoAsignado) {
        this.correoAsignado = correoAsignado;
    }

    /**
     * Metodo que devuelve una representación de texto de la tarea.
     *
     * @return descripción de la tarea
     */
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Fecha Creación: " + fechaCreada + "\n" +
                "Fecha Límite: " + fechaLimite + "\n" +
                "Completada: " + (estado ? "Sí" : "No") + "\n" +
                "Usuario: " + correoAsignado;
    }

}
