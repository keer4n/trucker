package io.github.keer4n.trucker.controller;


import io.github.keer4n.trucker.entity.Alert;
import io.github.keer4n.trucker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "alerts")
public class AlertController {
    @Autowired
    AlertService alertService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Alert> getSortedAlerts(){
        return alertService.getTopAlerts();
    }
}
