package com.turtleOnARock.weatherViewer.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Locations")
@Setter
@Getter
@NoArgsConstructor
public class Location {

    @Id
    @Column(name = "id")
    private int id;

    @Column (name = "UserId", nullable = false)
    private int userId;

    @Column (name = "Latitude")
    private double latitude;

    @Column (name = "Longitude")
    private double longitude;

    public Location (int userId, double latitude, double longitude){
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
