package cl.duoc.maquinaria.controller;


import cl.duoc.maquinaria.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class MainController {

    @FXML
    private ComboBox<String> cmbTipo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtHorasUso;
    @FXML
    private TextField txtPotencia;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtCapacidadBalde;
    @FXML
    private CheckBox chkMantencion;
    @FXML
    private CheckBox chkCertificacion;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<Maquina> tablaMaquinas;
    @FXML
    private TableColumn<Maquina, String> colTipo;
    @FXML
    private TableColumn<Maquina, String> colCodigo;
    @FXML
    private TableColumn<Maquina, Integer> colHorasUso;
    @FXML
    private TableColumn<Maquina, Double> colPotencia;
    @FXML
    private TableColumn<Maquina, Double> colCostoServicio;

    //metodo para bloquear siertos elementos segun sea especificado
    //
    @FXML
    private void cambiarTipo() {
        String tipo = cmbTipo.getValue();
        boolean excavadora = "Excavadora".equals(tipo);
        boolean cargador = "Cargador Frontal".equals(tipo);

        txtPeso.setDisable(!excavadora);
        chkMantencion.setDisable(!excavadora);
        chkCertificacion.setDisable(!excavadora);
        txtCapacidadBalde.setDisable(!cargador);
    }


    @FXML
    // se le da inicio y se le agregan al combo box
    private void initialize() {
        cmbTipo.getItems().addAll("Excavadora", "Cargador Frontal");
        cambiarTipo();
        configurarTabla();
    }

    //lista observable
    private final ObservableList<Maquina> maquinas = FXCollections.observableArrayList();

    private void configurarTabla() {
        tablaMaquinas.setItems(maquinas);
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colHorasUso.setCellValueFactory(new PropertyValueFactory<>("horasUso"));
        colPotencia.setCellValueFactory(new PropertyValueFactory<>("potencia"));
        colCostoServicio.setCellValueFactory(fila -> new ReadOnlyObjectWrapper<>(fila.getValue().calcularCostoServicio()));
    }

    //validacion de que el campo nombre no puede estar vacio ni ser null
    private String validarTexto(TextField campo, String nombre) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException(nombre + "No puede esta vacio");
        }
        return texto;
    }

    // se valida queel apartado se horas de uso sea un numero entero
    private int validarEntero(TextField campo, String nombre) {
        String texto = validarTexto(campo, nombre);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    nombre + ": ingrese un número entero válido.");
        }
    }

    private double validarDecimal(TextField campo, String nombre) {
        String texto = validarTexto(campo, nombre);
        texto = texto.replace(',', '.');
        double numero;

        try {
            numero = Double.parseDouble(texto);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nombre + "ingrese un número valido.");
        }

        if (!Double.isFinite(numero) || numero <= 0) {
            throw new IllegalArgumentException(nombre + "El numero debe ser mayo a 0.");
        }
        return numero;

    }
    //limpiar formulario cuando el ingreso de la maquina sea exitoso
    private void limpiarFormulario(){
        txtCodigo.clear();
        txtHorasUso.clear();
        txtPotencia.clear();
        txtPeso.clear();
        txtCapacidadBalde.clear();
        chkMantencion.setSelected(false);
        chkCertificacion.setSelected(false);
        cmbTipo.getSelectionModel().clearSelection();
        cambiarTipo();

    }
    private Maquina crearMaquina(){
        String tipo = cmbTipo.getValue();
        if(tipo== null){
            throw new IllegalArgumentException("tipo: seleccione maquina");
        }

        String codigo = validarTexto(txtCodigo, "Codigo");
        int horas = validarEntero(txtHorasUso,"Horas de uso");
        Double potencia = validarDecimal(txtPotencia, "Potencia");
        if("Excavadora".equals(tipo)){
            double peso = validarDecimal(txtPeso, "peso");
            Excavadora excavadora = new Excavadora(codigo,horas,potencia,peso,chkMantencion.isSelected());
            if(chkCertificacion.isSelected()){
                excavadora.certificarSeguridad();
            }
            return excavadora;
        }
        double capacidad = validarDecimal(txtCapacidadBalde,"Capacidad del balde");
        return new CargadorFrontal(codigo,horas,potencia,capacidad);
    }



}






