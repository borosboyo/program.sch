package hu.bme.aut.programsch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum EmailType {

    NEWEVENT("Új program a naptárban!", "newEvent");

    private String emailSubject;
    private String emailTemplate;
}
