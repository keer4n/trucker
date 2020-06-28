package io.github.keer4n.trucker.repository;

import io.github.keer4n.trucker.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, String> {

    List<Reading> findByVin(String vin);
}
