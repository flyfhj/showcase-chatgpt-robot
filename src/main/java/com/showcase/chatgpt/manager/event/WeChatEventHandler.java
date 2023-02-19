package com.showcase.chatgpt.manager.event;

import com.showcase.chatgpt.manager.ChatGPTManager;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义事件监听器
 *
 * @author fuhuijun
 * @date 2023/2/8 13:10
 */
@Slf4j
public class WeChatEventHandler {
    private final ChatGPTManager chatGPTManager;

    public WeChatEventHandler(ChatGPTManager chatGPTManager) {
        this.chatGPTManager = chatGPTManager;
    }

    /**
     * 处理单人/群组消息
     */
    public String doHandle(WeChatMessage chatMessage) {
        String message = chatMessage.getText();
        log.info("接收到 [{}] 的消息：{}", chatMessage.getFromUserName(), message);

        // TODO: 可对消息进行敏感词处理

        //invoke chatGpt server
        return chatGPTManager.createCompletion(message);
    }
}
