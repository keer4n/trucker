package io.github.keer4n.trucker.repository;

import io.github.keer4n.trucker.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, String> {

    List<Reading> findByVin(String vin);
}
