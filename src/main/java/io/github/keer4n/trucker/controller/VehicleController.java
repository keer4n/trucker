package io.github.keer4n.trucker.controller;

import io.github.keer4n.trucker.entity.Vehicle;
import io.github.keer4n.trucker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "vehicles")
@CrossOrigin
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> create(@RequestBody  List<Vehicle> lv)
    {
        return vehicleService.importVehicles(lv);
    }

    @RequestMapping(method =  RequestMethod.GET)
    public List<Vehicle> getAll(){
        return vehicleService.getAll();
    }

}
