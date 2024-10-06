package io.github.ehayik.kata.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PersonServiceTests {

    @Test
    void shouldReturnPersons() {
        // Given
        var personService = new PersonService();

        // When
        var persons = personService.getPersons(2);

        // Then
        assertThat(persons).hasSize(2);
    }
}
