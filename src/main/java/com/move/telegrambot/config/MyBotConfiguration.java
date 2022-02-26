package com.move.telegrambot.config;

import com.github.kshashov.telegram.config.TelegramBotGlobalProperties.Builder;

import java.util.concurrent.TimeUnit;

import com.github.kshashov.telegram.config.TelegramBotGlobalProperties;
import com.github.kshashov.telegram.config.TelegramBotGlobalPropertiesConfiguration;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;

@Component
public class MyBotConfiguration implements TelegramBotGlobalPropertiesConfiguration {
    private String token = "5112896084:AAEc3Bq1oMSOI7bhomU7BMoinUO1qQK1340";


    @Override
    public void configure(TelegramBotGlobalProperties.Builder builder) {
        OkHttpClient okHttp = new OkHttpClient.Builder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .build();

        builder
            .configureBot(token, botBuilder -> {
                botBuilder
                    .configure(builder1 -> builder1.okHttpClient(okHttp));
            })
            .configureBot(token, botBuilder -> {
                botBuilder
                    .configure(builder1 -> builder1.updateListenerSleep(200L));
            });
    }

}
