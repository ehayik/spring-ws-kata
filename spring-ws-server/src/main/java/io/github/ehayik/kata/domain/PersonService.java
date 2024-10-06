package io.github.ehayik.kata.domain;

import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    public List<Person> getPersons(int quantity) {
        log.info("Generating {} persons", quantity);
        return IntStream.range(0, quantity)
                .mapToObj(x -> {
                    var faker = new Faker();
                    var gender = faker.gender().binaryTypes();
                    var contactInfo = new Person.ContactInfo(
                            faker.internet().emailAddress(), faker.phoneNumber().cellPhone());

                    return Person.builder()
                            .gender(gender)
                            .name(generateName(gender, faker))
                            .contactInfo(contactInfo)
                            .address(generateAddress(faker))
                            .birthday(faker.timeAndDate().birthday())
                            .build();
                })
                .toList();
    }

    private static Person.Name generateName(String gender, Faker faker) {
        var firstName = gender.equals("Male")
                ? faker.name().malefirstName()
                : faker.name().femaleFirstName();
        return new Person.Name(firstName, faker.name().lastName());
    }

    private static Person.Address generateAddress(Faker faker) {
        var fakerAddress = faker.address();
        return Person.Address.builder()
                .street(fakerAddress.streetAddress())
                .buildingNumber(fakerAddress.buildingNumber())
                .city(fakerAddress.cityName())
                .zipcode(fakerAddress.zipCode())
                .country(fakerAddress.country())
                .build();
    }
}
