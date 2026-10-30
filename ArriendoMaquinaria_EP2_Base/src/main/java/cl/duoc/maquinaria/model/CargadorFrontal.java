package cl.duoc.maquinaria.model;

/**
 * Cargador frontal que arrienda la empresa.
 * Costo del servicio: base $120.000; si la capacidad del balde supera los 3 metros cúbicos, recargo del 20%.
 */
public class CargadorFrontal extends Maquina {

    private static final double COSTO_BASE = 120000;
    private static final double RECARGO_CAPACIDAD_BALDE = 0.20;
    private static final double LIMITE_CAPACIDAD = 3.0;

    private double capacidadBalde;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    public CargadorFrontal() {
    }

    public CargadorFrontal(String codigo, int horasUso, double potencia, double capacidadBalde) {
        super(codigo, horasUso, potencia);
        setCapacidadBalde(capacidadBalde);
    }

    public double getCapacidadBalde() {
        return capacidadBalde;
    }

    public void setCapacidadBalde(double capacidadBalde) {
        this.capacidadBalde = capacidadBalde;
    }

    @Override
    public double calcularCostoServicio() {
        if (capacidadBalde > LIMITE_CAPACIDAD) {
            return COSTO_BASE + COSTO_BASE * RECARGO_CAPACIDAD_BALDE;
        }
        return COSTO_BASE;
    }

    @Override
    public String getTipo() {
        return "Cargador Frontal";
    }
}
