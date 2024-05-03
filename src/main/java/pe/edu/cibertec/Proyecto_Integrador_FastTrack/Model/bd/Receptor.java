package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receptor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receptor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreceptor;
    @Column(name = "dnireceptor")
    private Integer dnireceptor;
    @Column(name = "nombrereceptor")
    private String nombrereceptor;
    @Column(name = "apellidoreceptor")
    private String apellidoreceptor;
    @Column(name = "celularreceptor")
    private String celularreceptor;
    @Column(name = "distritoreceptor")
    private String distritoreceptor;
}
