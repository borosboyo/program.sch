package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.ClubDto;
import hu.bme.aut.programsch.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @GetMapping
    public List<ClubDto> getClubs() {
        return clubService.findAll();
    }

    @GetMapping("/{id}")
    public ClubDto getClubById(@PathVariable long id) {
        return clubService.findById(id);
    }

    @PostMapping
    public ClubDto createClub(@RequestBody ClubDto clubDto) {
        return clubService.createClub(clubDto);
    }

    @DeleteMapping
    public void deleteAllClubs() {
        clubService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable long id) {
        clubService.deleteClub(id);
    }

    @PutMapping("/{id}")
    public ClubDto updateClub(@PathVariable long id) {
        //return clubService.updateClub(clubService.findById(id));
    }
}
