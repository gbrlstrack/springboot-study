package br.com.strack.springboot_study.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(
                "PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));

        objectMapper.setFilterProvider(filters);

        return objectMapper;
    }
}
