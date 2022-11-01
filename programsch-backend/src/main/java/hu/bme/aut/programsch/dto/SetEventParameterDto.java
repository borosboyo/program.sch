package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SetEventParameterDto {
    private LocalDateTime parse;
    private LocalDateTime parse2 ;
    private String place;
    private String facebookUrl;
    private String poster;
    private String tldr;
    private String description;

    public SetEventParameterDto(LocalDateTime parse, LocalDateTime parse2, String place, String facebookUrl, String poster, String tldr, String description) {
        this.parse = parse;
        this.parse2 = parse2;
        this.place = place;
        this.facebookUrl = facebookUrl;
        this.poster = poster;
        this.tldr = tldr;
        this.description = description;
    }
}
