package io.github.keer4n.trucker.service;

import io.github.keer4n.trucker.entity.Alert;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlertService {
    List<Alert> getTopAlerts();
}
