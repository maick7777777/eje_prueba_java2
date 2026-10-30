package cl.duoc.maquinaria.model;

/**
 * Excavadora que arrienda la empresa. Participa en el programa de certificación de seguridad.
 * Costo del servicio: base $150.000; si no cuenta con mantención al día, recargo del 25%.
 */
public class Excavadora extends Maquina implements Certificable {

    private static final double COSTO_BASE = 150000;
    private static final double RECARGO_MANTENCION_PENDIENTE = 0.25;

    private double pesoToneladas;
    private boolean mantencionAlDia;
    private boolean certificacionSeguridad;

    /**
     * Constructor sin parámetros, requerido para reconstruir objetos.
     */
    public Excavadora() {
    }

    public Excavadora(String codigo, int horasUso, double potencia, double pesoToneladas, boolean mantencionAlDia) {
        super(codigo, horasUso, potencia);
        setPesoToneladas(pesoToneladas);
        setMantencionAlDia(mantencionAlDia);
        this.certificacionSeguridad = false;
    }

    public double getPesoToneladas() {
        return pesoToneladas;
    }

    public void setPesoToneladas(double pesoToneladas) {
        this.pesoToneladas = pesoToneladas;
    }

    public boolean isMantencionAlDia() {
        return mantencionAlDia;
    }

    public void setMantencionAlDia(boolean mantencionAlDia) {
        this.mantencionAlDia = mantencionAlDia;
    }

    @Override
    public boolean tieneCertificacionSeguridad() {
        return certificacionSeguridad;
    }

    @Override
    public void certificarSeguridad() {
        this.certificacionSeguridad = true;
    }

    @Override
    public double calcularCostoServicio() {
        if (mantencionAlDia) {
            return COSTO_BASE;
        }
        return COSTO_BASE + COSTO_BASE * RECARGO_MANTENCION_PENDIENTE;
    }

    @Override
    public String getTipo() {
        return "Excavadora";
    }
}
