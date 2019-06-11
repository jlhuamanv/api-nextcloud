package pe.org.jhsystem.cloud.api.nextcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket defApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                   .select()                 
                   .apis(RequestHandlerSelectors.basePackage("pe.org.jhsystem.cloud.api.nextcloud.rest"))
                   .paths(regex("/*.*"))
                   .build()
                   .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
        		.title("API para conexion con NextCloud")
                .description("Permite la gestion de carpetas y archivos en NextCloud.")
                .termsOfServiceUrl("Terminos del servicio")
                .version("1.0.0")
                .contact(new Contact("JHSYSTEM", "https://diariojl.blogspot.com", "jlhuamanv@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
