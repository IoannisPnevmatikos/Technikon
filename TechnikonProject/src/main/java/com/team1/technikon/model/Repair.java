package com.team1.technikon.model;

import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;
    private String shortDescription;
    private TypeOfRepair typeOfRepair;
    private StatusOfRepair statusOfRepair;
    @Column(scale = 2)
    private BigDecimal cost;
    private String descriptionText;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Property property;
}
