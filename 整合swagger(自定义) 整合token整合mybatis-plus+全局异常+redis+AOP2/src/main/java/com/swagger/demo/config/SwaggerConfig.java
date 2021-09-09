package com.swagger.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger自定义配置类
 */
@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {
	/**
	 * 创建API应用 apiInfo() 增加API相关信息
	 * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
	 * 本例采用指定扫描的包路径来定义指定要建立API的目录。
	 *
	 * @return
	 */
	@Bean
	public Docket createRestApi() {
		// 选择那些路径和api会生成document
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
				// 对所有api进行监控
				.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build()
				// 配置token
				.globalOperationParameters(setHeaderToken());
	}

	/**
	 * 配置token
	 * 
	 * @return
	 */
	private List<Parameter> setHeaderToken() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}

	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中）
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("测试接口文档").description("测试接口文档").version("1.0").build();
	}

}