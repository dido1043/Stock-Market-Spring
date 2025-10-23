package com.example.stockmarketspringapi.config;

import com.example.stockmarketspringapi.exception.FeignErrorDecoder;
import feign.Client;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    private final FeignErrorDecoder customFeignErrorDecoder;

    public FeignConfig(FeignErrorDecoder customFeignErrorDecoder) {
        this.customFeignErrorDecoder = customFeignErrorDecoder;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return customFeignErrorDecoder;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Client feignClient() {
        return new OkHttpClient();
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                10, TimeUnit.SECONDS,  // connect timeout
                60, TimeUnit.SECONDS,  // read timeout
                true                   // follow redirects
        );
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 3);
    }
}
