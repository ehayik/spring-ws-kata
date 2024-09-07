package io.github.ehayik.kata.ws;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        var servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "persons")
    DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema personsSchema) {
        var wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PersonsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://notes.eduardoeljaiek.com/spring-ws-kata/gen");
        wsdl11Definition.setSchema(personsSchema);
        return wsdl11Definition;
    }

    @Bean
    XsdSchema personsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("persons.xsd"));
    }
}