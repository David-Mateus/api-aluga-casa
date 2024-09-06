package davidmateus.com.alugacasa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_situation")
public class Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column(name = "month", nullable = false, length = 15)
    private String month;
    @Column(name = "status", nullable = false, length = 15)
    private  String status;
}
