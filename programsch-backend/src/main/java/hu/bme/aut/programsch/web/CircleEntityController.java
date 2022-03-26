package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.CircleEntity;
import hu.bme.aut.programsch.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CircleEntityController {

    @Autowired
    private CircleService circleService;

    @GetMapping("/circles")
    public List<CircleEntity> getCircles(){
        return circleService.findAll();
    }
}
