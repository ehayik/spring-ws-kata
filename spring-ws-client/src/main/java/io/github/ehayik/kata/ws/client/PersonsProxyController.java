package io.github.ehayik.kata.ws.client;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonsProxyController {

    private final PersonsClient personsClient;

    @GetMapping
    public List<PersonDto> getPersons(@RequestParam int quantity) {
        return personsClient.getPersons(quantity).getPersons();
    }
}
