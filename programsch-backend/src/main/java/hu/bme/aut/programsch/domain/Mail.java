package hu.bme.aut.programsch.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"mailFrom", "mailTo", "mailSubject"})
public class Mail {

    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailContent;
}
