package com.example.timeregistration.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMapper {

    /**
     * Model mapper Bean used for mapping dto to objects in project
     * mapper doesn`t need getters in dto
     * configuration enable to map fields with even private modificators
     *
     * @return modelMapper Bean
     * @author Bohdan
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

}
