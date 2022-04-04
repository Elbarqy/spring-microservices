package com.elbarqy.appstore.orderservice;

import com.elbarqy.appstore.orderservice.core.SecureXstreamSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.axonframework.config.DefaultConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class OrdersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersServiceApplication.class, args);
    }
    @Bean
    public void configAxon(){
        DefaultConfigurer.defaultConfiguration()
                .configureSerializer(configuration -> SecureXstreamSerializer.get())
                .configureMessageSerializer(configuration -> SecureXstreamSerializer.get())
                .configureEventSerializer(configuration -> SecureXstreamSerializer.get());
    }
    @Autowired
    public void configureXStreamSecurity(XStream xStream) {
        xStream.allowTypesByWildcard(new String[]{"org.axonframework.**",
                "com.elbarqy.appstore.core.**"});
    }


//    @Bean
//    public XStream xStream() {
//        XStream xStream_ = new XStream();
//        xStream_.allowTypesByWildcard(new String[]{
//                "org.axonframework.**",
//                "com.elbarqy.appstore.core.**",
//        });
//        return xStream_;
//    }

}
