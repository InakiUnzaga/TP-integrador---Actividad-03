package com.daos.acosta_bonafede_spadola_unzaga.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "asistencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "asistido_id", nullable = false)
    private Asistido asistido;

    @ManyToOne
    @JoinColumn(name = "racion_id", nullable = false)
    private Racion racion;

}

