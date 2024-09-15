package io.github.ehayik.kata.ws.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Slf4j
@Configuration
public class PersonsClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        var marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.github.ehayik.kata.ws.client");
        return marshaller;
    }

    @Bean
    public PersonsClient personClient(Jaxb2Marshaller marshaller, @Value("${ws.server.uri}") String defaultUri) {
        log.debug("Creating person client with default uri: {}", defaultUri);
        var client = new PersonsClient();
        client.setDefaultUri(defaultUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
