package me.jaden.swipejobs.po;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@PropertySource("classpath:http_resource.properties")
@ConfigurationProperties
@Data
public class HttpProperties {
    private String workerUrl;
    private String jobUrl;


    public URI toWorkerURI(){  return  toURI(workerUrl); }
    public URI toJobURI(){  return  toURI(jobUrl); }

    private URI toURI(String stringURI){
        URI retURI = null;
        try{
            retURI = new java.net.URI(stringURI);
        }catch(Exception e){ e.printStackTrace(); }
        return retURI;
    }
}
