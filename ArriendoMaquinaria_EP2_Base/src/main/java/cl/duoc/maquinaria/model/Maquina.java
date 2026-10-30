package cl.duoc.maquinaria.model;

/**
 * Representa a cualquier máquina que arrienda la empresa.
 * Define los datos comunes y delega en cada tipo de maquinaria el cálculo del
 * costo del servicio.
 */
public abstract class Maquina {

    private String codigo;
    private int horasUso;
    private double potencia;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    protected Maquina() {
    }

    public Maquina(String codigo, int horasUso, double potencia) {
        setCodigo(codigo);
        setHorasUso(horasUso);
        setPotencia(potencia);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo ni vacío.");
        }
        this.codigo = codigo.trim();
    }

    public int getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(int horasUso) {
        if (horasUso < 0 || horasUso > 20000) {
            throw new IllegalArgumentException("Las horas de uso deben estar entre 0 y 20000.");
        }
        this.horasUso = horasUso;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        if (potencia <= 0) {
            throw new IllegalArgumentException("La potencia debe ser mayor que cero.");
        }
        this.potencia = potencia;
    }

    /**
     * Calcula el costo del servicio según las reglas propias de cada tipo de maquinaria.
     *
     * @return costo del servicio en pesos
     */
    public abstract double calcularCostoServicio();

    /**
     * Retorna el nombre del tipo de maquinaria (por ejemplo, "Excavadora" o "Cargador Frontal").
     *
     * @return tipo de maquinaria
     */
    public abstract String getTipo();

    @Override
    public String toString() {
        return "Código: " + codigo + " | Horas de uso: " + horasUso;
    }
}
