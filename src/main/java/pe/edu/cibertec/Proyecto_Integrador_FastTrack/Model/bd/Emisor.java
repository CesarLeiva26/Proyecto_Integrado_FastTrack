package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emisor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idemisor;
    @Column(name = "dniemisor")
    private Integer dniemisor;
    @Column(name = "nombreemisor")
    private String nombreemisor;
    @Column(name = "apellidoemisor")
    private String apellidoemisor;
}
