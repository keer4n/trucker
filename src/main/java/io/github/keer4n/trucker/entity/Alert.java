package io.github.keer4n.trucker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
public class Alert {
    public  enum Type{TIRE_PRESSURE, RPM, FUEL, INDICATOR};
    public  enum Priority{HIGH, MEDIUM, LOW};

    @Id
    private String id;
    private Type type;
    private Priority priority;

    @ManyToOne
    private Vehicle vehicle;
//    @OneToOne
//    private Reading reading;

    public Alert(){
        this.id = UUID.randomUUID().toString();
    }


}
