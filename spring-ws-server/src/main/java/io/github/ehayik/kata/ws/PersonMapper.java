package io.github.ehayik.kata.ws;

import io.github.ehayik.kata.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    List<PersonDto> toDtos(List<Person> persons);

    @Mapping(source = "name.firstName", target = "firstname")
    @Mapping(source = "name.lastName", target = "lastname")
    @Mapping(source = "contactInfo.email", target = "email")
    @Mapping(source = "contactInfo.phoneNumber", target = "phone")
    PersonDto toDto(Person person);

    default Gender toGender(String gender) {
        if (isBlank(gender)) return null;
        return Gender.valueOf(gender.toUpperCase());
    }
}
