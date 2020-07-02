package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Alert;
import io.github.keer4n.trucker.entity.Reading;
import io.github.keer4n.trucker.entity.Vehicle;
import io.github.keer4n.trucker.repository.AlertRepository;
import io.github.keer4n.trucker.repository.ReadingRepository;
import io.github.keer4n.trucker.repository.VehicleRepository;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    AlertRepository alertRepository;

    @Override
    public Reading importOne(Reading reading){
        processRules(reading);
        return readingRepository.save(reading);
    }


    @Override
    public List<Reading> getAll() {
        return readingRepository.findAll();
    }

    @Override
    public List<Reading> findByVin(String vin) {
        return readingRepository.findByVin(vin);
    }


    public void processRules(Reading reading){
        Facts facts = new Facts();
        facts.put("vin",reading.getVin());
        facts.put("engineRpm",reading.getEngineRpm());
        facts.put("fuelVolume", reading.getFuelVolume());
        facts.put("tires", reading.getTires());
        facts.put("engineCoolantLow", reading.isEngineCoolantLow());
        facts.put("checkEngineLightOn", reading.isCheckEngineLightOn());
        Optional<Vehicle> veh = vehicleRepository.findById(reading.getVin());
        if(veh.isPresent()) {
            facts.put("redLineRpm", veh.get().getRedlineRpm());
            facts.put("maxFuelVolume", veh.get().getMaxFuelVolume());
        }else{
           return;
        }
        Rules rules = new Rules();
        Rule rpmRule = new RuleBuilder()
                .name("RPM rule")
                .description("Triggered when the engine RPM is greater than redLineRPM")
                .when(f -> (Integer)f.get("engineRpm") > (Integer)f.get("redLineRpm"))
                .then(f -> {System.out.println("==========================\nRPM Alert for "+ f.get("vin") +"\n==========================");
                            Alert alert = new Alert();
                            alert.setType(Alert.Type.RPM); alert.setPriority(Alert.Priority.HIGH);
                            alert.setVehicle(veh.get());
                            alertRepository.save(alert);
//                            veh.get().getAlerts().add(alert);
//                            vehicleRepository.save(veh.get());
                            })
                .build();
        rules.register(rpmRule);

        Rule fuelRule = new RuleBuilder()
                .name("Fuel rule")
                .description("Triggered when the fuel is less than 10% of maximum")
                .when(f -> (Float)f.get("fuelVolume") < (Integer)f.get("maxFuelVolume")*0.1)
                .then(f -> {System.out.println("==========================\nFUEL Alert\n==========================");
                            Alert alert = new Alert();
                            alert.setType(Alert.Type.FUEL); alert.setPriority(Alert.Priority.MEDIUM);
                            alert.setVehicle(veh.get());
                            alertRepository.save(alert);
                            })
                .build();
        rules.register(fuelRule);

        Rule tirePressureRule = new RuleBuilder()
                .name("Tire Pressure rule")
                .description("Any tire pressure is not in allowed range of (32,36)")
                .when(f -> {
                    Map<String,Integer> tires = f.get("tires");
                    for(Integer p: tires.values()){
                        if(p < 32 || p > 36) return true;
                    }
                    return false;
                })
                .then(f -> {System.out.println("==========================\nTIRE PRESSURE Alert\n==========================");
                            Alert alert = new Alert();
                            alert.setType(Alert.Type.TIRE_PRESSURE); alert.setPriority(Alert.Priority.LOW);
                            alert.setVehicle(veh.get());
                            alertRepository.save(alert);
                            })
                .build();
        rules.register(tirePressureRule);

        Rule indicatorRule = new RuleBuilder()
                .name("Indicator rule")
                .description("Triggered when either a engine light is on or engine coolant light is on")
                .when(f -> (Boolean)f.get("engineCoolantLow") || (Boolean)f.get("checkEngineLightOn"))
                .then(f -> {System.out.println("==========================\nINDICATOR Alert\n==========================");
                            Alert alert = new Alert();
                            alert.setType(Alert.Type.INDICATOR); alert.setPriority(Alert.Priority.LOW);
                            alert.setVehicle(veh.get());
                            alertRepository.save(alert);
                             })
                .build();
        rules.register(indicatorRule);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);

    }
}
