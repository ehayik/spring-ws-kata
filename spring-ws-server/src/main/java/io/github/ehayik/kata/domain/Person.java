package io.github.ehayik.kata.domain;

import java.time.LocalDate;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record Person(
        @NonNull Name name,
        @NonNull ContactInfo contactInfo,
        @NonNull Address address,
        @NonNull LocalDate birthday,
        @NonNull String gender) {

    public record ContactInfo(String email, String phoneNumber) {}

    public record Name(String firstName, String lastName) {}

    @Builder
    public record Address(String street, String buildingNumber, String city, String zipcode, String country) {}
}
