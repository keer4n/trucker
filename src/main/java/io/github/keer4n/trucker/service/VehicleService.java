package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    public List<Vehicle> importVehicles(List<Vehicle> vehicleList);

    public List<Vehicle> getAll();
}
