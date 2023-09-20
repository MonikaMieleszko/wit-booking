package pl.sdacademy.booking.validator;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class NewEventDtoValidator {
    public static List<String> validate(NewEventDto newEventDto) {
       List<String> result = new ArrayList<>();

        if (newEventDto.getFromTime()==null) {
            result.add("From is null");
        }
        if (newEventDto.getToTime()==null) {
            result.add("To is null");
        }
        //data z przeszłości - to jest trudne do przetestowania
        //czy daty przypadają na godziny pracy salonu
        //okreśona długość trwania sesji
        if(newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration duration = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            if (duration.isNegative()) {
                result.add("To is before from");
            }
            if (duration.toMinutes() > 30) {
                result.add("Event is too long");
            }
            if (duration.isZero()) {
                result.add("Event is too short");
            }
        }
        //item name nie jest wypełnione
        //jeżeli chciałabym sprawdzić, czy należy do listy itemów, to wtedy metoda już nie będzie mogła być statyczna
        return result;
    }
}
