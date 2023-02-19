package com.showcase.chatgpt.config;

import com.showcase.chatgpt.manager.QQChatGPTManager;
import com.showcase.chatgpt.manager.WxChatGPTManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

/**
 * @author fuhuijun
 * @date 2023/2/7 19:07
 */
@EnableConfigurationProperties(ChatGPTProperties.class)
public class ChatGPTAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "chatgpt.qq", name = "enabled", havingValue = "true")
    public QQChatGPTManager qqChatGPTManager(ChatGPTProperties properties) {
        Assert.notNull(properties.getApiKey(), "[Assertion failed] - this argument [chatGtp.apikey] is required!");
        Assert.notNull(properties.getQq(), "[Assertion failed] - this argument [chatGtp.qq] is required!");
        return new QQChatGPTManager(properties);
    }

    @Bean
    @ConditionalOnProperty(prefix = "chatgpt.wechat", name = "enabled", havingValue = "true")
    public WxChatGPTManager wxChatGPTManager(ChatGPTProperties properties) {
        Assert.notNull(properties.getApiKey(), "[Assertion failed] - this argument [chatGtp.apikey] is required!");
        return new WxChatGPTManager(properties);
    }
}
