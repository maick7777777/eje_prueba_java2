package cl.duoc.maquinaria.model;

/**
 * Contrato que cumplen las máquinas que participan en el programa de
 * certificación de seguridad de la empresa.
 */
public interface Certificable {

    /**
     * Indica si la máquina cuenta con certificación de seguridad activa.
     *
     * @return true si la certificación está activa
     */
    boolean tieneCertificacionSeguridad();

    /**
     * Certifica la seguridad de la máquina.
     */
    void certificarSeguridad();
}
