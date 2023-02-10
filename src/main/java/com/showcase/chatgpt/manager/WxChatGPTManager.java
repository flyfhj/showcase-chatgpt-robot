package com.showcase.chatgpt.manager;

import com.showcase.chatgpt.config.ChatGPTProperties;

/**
 * chatGpt for WeChat
 *
 * @author fuhuijun
 * @date 2023/2/8 14:02
 */
public class WxChatGPTManager extends ChatGPTManager {

    public WxChatGPTManager(String apiKey) {
        super(apiKey);
    }

    public WxChatGPTManager(ChatGPTProperties properties) {
        super(properties);
    }
}
