package orf.sid.ecomiibdccapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="customer.params")

public record CustomerConfigParams(int x ,int y) {
}
