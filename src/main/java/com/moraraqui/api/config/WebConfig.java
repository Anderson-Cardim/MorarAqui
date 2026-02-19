package com.moraraqui.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${imoveis.fotos.diretorio}")
    private String diretorioFotos;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define a URL pública
        registry.addResourceHandler("/uploads/**")
                // Define o caminho físico (importante o prefixo file:/)
                .addResourceLocations("file:" + diretorioFotos + "/");
    }
}