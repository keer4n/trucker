package io.github.keer4n.trucker.entity;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Reading {

	@Id
	private String id;
	private String vin;
	private String latitude;
	private String longitude;
	private Date timestamp;
	private float fuelVolume;
	private int speed;
	private int engineHp;
	private boolean checkEngineLightOn;
	private boolean engineCoolantLow;
	private boolean cruiseControlOn;
	private int engineRpm;
	private HashMap<String, Integer> tires;

	public Reading(){
		this.id = UUID.randomUUID().toString();
	}
}
