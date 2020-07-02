package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Alert;
import io.github.keer4n.trucker.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    @Autowired
    AlertRepository alertRepository;

    @Override
    public List<Alert> getTopAlerts() {
        return alertRepository.findAll(Sort.by(Sort.Direction.ASC,"priority") );
    }
}
