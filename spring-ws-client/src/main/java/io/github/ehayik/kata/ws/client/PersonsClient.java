package io.github.ehayik.kata.ws.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class PersonsClient extends WebServiceGatewaySupport {

    public GetPersonsResponse getPersons(int quantity) {
        log.info("Getting {} persons.", quantity);
        var request = new GetPersonsRequest();
        request.setQuantity(quantity);
        return (GetPersonsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
