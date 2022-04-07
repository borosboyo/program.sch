package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.service.ResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController 
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private ResortService resortService;

    @GetMapping("/resorts")
    public int getResorts(){
        return resortService.findAll().size();
    }

    @GetMapping("/circles")
    public int getCircles(){
        return circleService.findAll().size();
    }
}
