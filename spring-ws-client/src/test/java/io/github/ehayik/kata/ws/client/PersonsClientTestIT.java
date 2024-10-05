package io.github.ehayik.kata.ws.client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = PersonsClientConfig.class)
@EnableWireMock({@ConfigureWireMock(name = "persons-client", properties = "ws.server.uri")})
class PersonsClientTestIT {

    private static final String GET_PERSONS_REQ =
            """
					<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
					<SOAP-ENV:Header/>
					<SOAP-ENV:Body>
							<ns2:getPersonsRequest xmlns:ns2="https://notes.eduardoeljaiek.com/spring-ws-kata/gen">
								<ns2:quantity>1</ns2:quantity>
							</ns2:getPersonsRequest>
						</SOAP-ENV:Body>
					</SOAP-ENV:Envelope>
					""";

    private static final String GET_PERSONS_RESP =
            """
			<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
				<SOAP-ENV:Header/>
				<SOAP-ENV:Body>
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
				</SOAP-ENV:Body>
			</SOAP-ENV:Envelope>
		""";

    @Autowired
    private PersonsClient personsClient;

    @InjectWireMock("persons-client")
    private WireMockServer wiremock;

    @Test
    void shouldGetPersons() {
        // Given
        wiremock.stubFor(post(urlEqualTo("/"))
                .withHeader("Content-Type", equalTo("text/xml; charset=UTF-8"))
                .withRequestBody(equalToXml(GET_PERSONS_REQ))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=UTF-8")
                        .withBody(GET_PERSONS_RESP)));

        // When
        var actualResponse = personsClient.getPersons(1);

        // Then
        assertThat(actualResponse)
                .isNotNull()
                .extracting(GetPersonsResponse::getPersons)
                .asInstanceOf(LIST)
                .isNotEmpty();
    }
}
