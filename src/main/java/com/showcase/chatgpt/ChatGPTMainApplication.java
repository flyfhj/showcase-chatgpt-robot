package com.showcase.chatgpt;

import com.showcase.chatgpt.config.ChatGPTAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author fuhuijun
 * @date 2023/2/7 18:40
 */
@SpringBootApplication
@Import(ChatGPTAutoConfiguration.class)
public class ChatGPTMainApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatGPTMainApplication.class, args);
    }
}
