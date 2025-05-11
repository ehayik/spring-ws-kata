package io.github.ehayik.kata.ws.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;

@Slf4j
class LoggingInterceptor extends ClientInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext) {

        if (log.isDebugEnabled()) {
            var message = (SoapMessage) messageContext.getRequest();
            log.debug("SOAP Request: {}", getSoapMessageAsString(message));
        }

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {

        if (log.isDebugEnabled()) {
            SoapMessage message = (SoapMessage) messageContext.getResponse();
            log.debug("SOAP Response: {}", getSoapMessageAsString(message));
        }

        return true;
    }

    private String getSoapMessageAsString(SoapMessage message) {
        try {
            var out = new ByteArrayOutputStream();
            message.writeTo(out);
            return out.toString(UTF_8);
        } catch (Exception ex) {
            log.error("Error converting SOAP message to string", ex);
            return "[Error getting SOAP message]";
        }
    }
}
