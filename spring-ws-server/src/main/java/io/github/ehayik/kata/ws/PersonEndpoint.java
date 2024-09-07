package io.github.ehayik.kata.ws;

import io.github.ehayik.kata.domain.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {

    private static final String NAMESPACE_URI = "https://notes.eduardoeljaiek.com/spring-ws-kata/gen";

    private final PersonService personService;
    private final PersonMapper personMapper;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
    public GetPersonsResponse getPersons(@RequestPayload GetPersonsRequest request) {
        var persons = personService.getPersons(request.quantity);
        return new GetPersonsResponse().withPersons(personMapper.toDtos(persons));
    }
}
