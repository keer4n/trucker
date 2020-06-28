package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Reading;

import java.util.List;

public interface ReadingService {

    Reading importOne(Reading reading);
    List<Reading> importAll(List<Reading> readings);

    List<Reading> getAll();

    List<Reading> findByVin(String vin);
}
