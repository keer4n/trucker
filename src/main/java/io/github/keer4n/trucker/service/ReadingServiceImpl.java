package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Reading;
import io.github.keer4n.trucker.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepository readingRepository;

    @Override
    public Reading importOne(Reading reading) {
        return readingRepository.save(reading);
    }

    @Override
    public List<Reading> importAll(List<Reading> readings) {
        return readingRepository.saveAll(readings);
    }

    @Override
    public List<Reading> getAll() {
        return readingRepository.findAll();
    }

    @Override
    public List<Reading> findByVin(String vin) {
        return readingRepository.findByVin(vin);
    }
}
