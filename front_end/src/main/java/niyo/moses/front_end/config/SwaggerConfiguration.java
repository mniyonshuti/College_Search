package niyo.moses.front_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket uiApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("niyo.moses.front_end"))
                .paths(regex("/college.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "College Search Application",
                "An application to search for the colleges in a given radius by moses Niyonshuti",
                "1.0",
                "Terms of service",
                new Contact("Moses Niyonshuti", "http://ab.co", "ab@gmail.com"),
                "Niyo moses Licenece",
                "http://niyolicence.com"
        );
        return apiInfo;
    }
}
