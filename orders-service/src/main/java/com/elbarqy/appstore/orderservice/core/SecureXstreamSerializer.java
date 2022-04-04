package com.elbarqy.appstore.orderservice.core;

import com.elbarqy.appstore.core.commands.ReserveProductCommand;
import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.xml.XStreamSerializer;

public class SecureXstreamSerializer {

    private static XStreamSerializer _instance;

    public static XStreamSerializer get() {
        if (_instance == null) {
            _instance = secureXStreamSerializer();
        }
        return _instance;
    }

    private static XStreamSerializer secureXStreamSerializer() {
        XStream xStream = new XStream();
        xStream.setClassLoader(SecureXstreamSerializer.class.getClassLoader());
        xStream.allowTypesByWildcard(new String[]{
                "org.axonframework.**",
                "com.elbarqy.appstore.**",
                "com.elbarqy.appstore.core.**",
		});
        return XStreamSerializer.builder().xStream(xStream).build();
    }

}
