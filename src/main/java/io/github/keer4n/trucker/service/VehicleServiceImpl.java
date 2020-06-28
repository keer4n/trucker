package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Vehicle;
import io.github.keer4n.trucker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> importVehicles(List<Vehicle> vehicleList) {
       return vehicleRepository.saveAll(vehicleList);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }
}
