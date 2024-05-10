package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorden")
    private Integer idorden;

    @Column(name = "fechaorden")
    private Date fechaorden;

    @Column(name = "kilos")
    private Double kilos;

    @Column(name = "precioporkilo")
    private Double precioporkilo;

    @Column(name = "claveorden")
    private String claveorden;

    @ManyToOne
    @JoinColumn(name = "idlocal")
    private Locales local;

    @ManyToOne
    @JoinColumn(name = "idemisor")
    private Emisor emisor;

    @ManyToOne
    @JoinColumn(name = "idestadoorden")
    private TipoEstadoOrden tipoEstadoOrden;

    @ManyToOne
    @JoinColumn(name = "idreceptor")
    private Receptor receptor;

    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    private Vehiculo vehiculo;

    public Double montopagar(){
        return (double) (kilos * precioporkilo);
    }
}