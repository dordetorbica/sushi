package com.sushi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sushi.config.WebConfig;
import com.sushi.service.impl.SushiService;
import static spark.debug.DebugScreen.enableDebugScreen;

@Configuration
@ComponentScan({ "com.sushi" })
public class App {
	
	public static void main(String[] args) {
		enableDebugScreen();
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    	new WebConfig(ctx.getBean(SushiService.class));
        ctx.registerShutdownHook();
    }       
}
