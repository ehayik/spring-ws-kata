package io.github.ehayik.kata.ws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import io.github.ehayik.kata.domain.PersonService;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@WebServiceServerTest(PersonEndpoint.class)
class PersonEndpointIT {

    private static final Map<String, String> NAMESPACES_MAPPING =
            Map.of("bd", "https://notes.eduardoeljaiek.com/spring-ws-kata/gen");

    private static final StringSource GIVEN_REQUEST = new StringSource(
            """
			<bd:getPersonsRequest xmlns:bd="https://notes.eduardoeljaiek.com/spring-ws-kata/gen">
				<bd:quantity>2</bd:quantity>
			</bd:getPersonsRequest>
			""");

    private static final StringSource EXPECTED_RESPONSE = new StringSource(
            """
					<bd:getPersonsResponse xmlns:bd="https://notes.eduardoeljaiek.com/spring-ws-kata/gen">
						<bd:persons>
							<bd:firstname>Lindsay</bd:firstname>
							<bd:lastname>Farrell</bd:lastname>
							<bd:email>beer.eliseo@yahoo.com</bd:email>
							<bd:phone>+2151259822482</bd:phone>
							<bd:birthday>1943-04-09</bd:birthday>
							<bd:gender>FEMALE</bd:gender>
							<bd:address>
								<bd:street>517 Stark Rapid</bd:street>
								<bd:buildingNumber>74653</bd:buildingNumber>
								<bd:city>Stephaniaberg</bd:city>
								<bd:zipcode>66275-5604</bd:zipcode>
								<bd:country>Zimbabwe</bd:country>
							</bd:address>
						</bd:persons>
					</bd:getPersonsResponse>
					""");

    @Autowired
    private MockWebServiceClient mockClient;

    @Test
    void shouldReturnResponseMatchingExpectedPayload() throws Exception {
        mockClient
                .sendRequest(withPayload(GIVEN_REQUEST))
                .andExpect(noFault())
                .andExpect(validPayload(new ClassPathResource("persons.xsd")))
                .andExpect(payload(EXPECTED_RESPONSE))
                .andExpect(xpath("bd:getPersonsResponse/bd:persons[1]/bd:firstname", NAMESPACES_MAPPING)
                        .evaluatesTo("Lindsay"));
    }

    @TestConfiguration
    static class PersonEndpointTestConfig {

        @Bean
        PersonService personService() {
            return new PersonService();
        }

        @Bean
        PersonMapper personMapper() {
            return new PersonMapperImpl();
        }
    }
}
