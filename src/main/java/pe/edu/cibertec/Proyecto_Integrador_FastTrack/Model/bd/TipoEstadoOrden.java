package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "tipoestadosorden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEstadoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idestadoorden;
    @Column(name = "nombreestado")
    private String nombreestado;
}
