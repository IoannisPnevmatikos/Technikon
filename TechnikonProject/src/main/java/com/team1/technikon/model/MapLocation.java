package com.team1.technikon.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class MapLocation {
    private double latitude;
    private double longitude;
}
