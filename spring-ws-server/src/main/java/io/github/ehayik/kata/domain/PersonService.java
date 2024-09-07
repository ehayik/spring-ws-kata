package io.github.ehayik.kata.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    public List<Person> getPersons(int quantity) {
        log.info("Generating {} persons", quantity);
        var name = new Person.Name("Lindsay", "Farrell");
        var contactInfo = new Person.ContactInfo("beer.eliseo@yahoo.com", "+2151259822482");
        var address = Person.Address.builder()
                .street("517 Stark Rapid")
                .buildingNumber("74653")
                .city("Stephaniaberg")
                .zipcode("66275-5604")
                .country("Zimbabwe")
                .build();
        return List.of(Person.builder()
                .name(name)
                .contactInfo(contactInfo)
                .address(address)
                .gender("female")
                .birthday(LocalDate.parse("1943-04-09"))
                .build());
    }
}
