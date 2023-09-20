package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("From is null");
    }

    @Test
    void shouldCheckThatToIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 57))
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(1).contains("To is null");
    }

    @Test
    void shouldCheckThatFromAndToIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przykład")
                .toTime(null)
                .fromTime(null)
                .build();
        List<String> result = NewEventDtoValidator.validate(input);
        assertThat(result).hasSize(2).containsExactly("To is null", "From is null");
    }
}