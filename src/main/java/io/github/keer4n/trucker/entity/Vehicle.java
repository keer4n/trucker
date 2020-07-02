package io.github.keer4n.trucker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Vehicle {

    @Id
    private String vin;


    private String make;
    private String model;
    private int year;
    private int redlineRpm;
    private int maxFuelVolume;
    private Date lastServiceDate;

    @OneToMany(mappedBy = "vin")
    private List<Reading> readings;

    public Vehicle(){

    }



}
