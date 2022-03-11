package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.SchUserDto;
import hu.bme.aut.programsch.service.SchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schusers")
@RequiredArgsConstructor
public class SchUserController {
    private final SchUserService schUserService;

    @GetMapping
    public List<SchUserDto> getSchUsers() {
        return schUserService.findAll();
    }

    @GetMapping("/{id}")
    public SchUserDto getSchUserById(@PathVariable long id) {
        return schUserService.findById(id);
    }

    @PostMapping
    public SchUserDto createSchUser(@RequestBody SchUserDto schUserDto) {
        return schUserService.createSchUser(schUserDto);
    }

    @DeleteMapping
    public void deleteAllSchUsers() {
        schUserService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteSchUser(@PathVariable long id) {
        schUserService.deleteSchUser(id);
    }

    @PutMapping("/{id}")
    public SchUserDto updateSchUser(@RequestBody SchUserDto schUserDto) {
        return schUserService.updateSchUser(schUserDto);
    }
}
