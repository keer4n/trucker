package io.github.keer4n.trucker.controller;

import io.github.keer4n.trucker.entity.Reading;
import io.github.keer4n.trucker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "readings")
@CrossOrigin
public class ReadingController {
    @Autowired
    ReadingService readingService;

    @RequestMapping(method = RequestMethod.POST)
    public Reading importOne(@RequestBody Reading reading){
        return readingService.importOne(reading);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{vin}")
    public List<Reading> findByVin(@Param("id") String vin){
        return readingService.findByVin(vin);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> findAll(){
        return readingService.getAll();
    }


}
