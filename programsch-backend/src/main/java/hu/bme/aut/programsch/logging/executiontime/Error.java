package hu.bme.aut.programsch.logging.executiontime;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Error {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
