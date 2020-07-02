package io.github.keer4n.trucker.repository;

import io.github.keer4n.trucker.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,String> {
}
