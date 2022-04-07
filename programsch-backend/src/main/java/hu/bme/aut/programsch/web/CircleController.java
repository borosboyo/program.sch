package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.service.ResortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/circle")
@RequiredArgsConstructor
public class CircleController {

    private final CircleService circleService;

    private final ResortService resortService;

    @GetMapping()
    public List<CircleDto> getCircles() {
        return circleService.findAll();
    }
}
