package com.ssafy.happyhouse.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.**.dao" })
public class SwaggerConfiguration {
	private String version = "V1";
	private String title = "HappyHouse Framework" + version;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
					.apiInfo(apiInfo()).groupName(version).select()
					.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse"))
					.paths(regex("/*/.*")).build() // 여기 설정한 범위 내에서 swagger어노테이션을 가진 값들이 swagger에 나오게 됨
					.useDefaultResponseMessages(false);
	}
	
	private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
//      consumes.add("application/xml;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 HappyHouse API<br>") 
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
				.version("1.0").build();
	}
}
