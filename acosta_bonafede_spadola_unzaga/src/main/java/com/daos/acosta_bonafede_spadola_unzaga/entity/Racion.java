package com.daos.acosta_bonafede_spadola_unzaga.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "raciones")
public class Racion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stockPreparado;

    private int stockRestante;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    private LocalDateTime fechaPreparacion;

    private LocalDateTime fechaVencimiento;

    @PrePersist
    protected void onCreate() {
        fechaPreparacion = LocalDateTime.now();
    }

}
