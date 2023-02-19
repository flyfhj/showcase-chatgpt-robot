package com.showcase.chatgpt.manager;

import com.showcase.chatgpt.config.ChatGPTProperties;
import com.showcase.chatgpt.manager.event.WeChatEventHandler;
import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.exception.WeChatException;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * chatGpt for WeChat
 *
 * @author fuhuijun
 * @date 2023/2/8 14:40
 */
@Slf4j
public class WxChatGPTManager extends ChatGPTManager {

    public WxChatGPTManager(String apiKey) {
        super(apiKey);
    }

    public WxChatGPTManager(ChatGPTProperties properties) {
        super(properties);
    }

    @PostConstruct
    public void init() {
        // 自动登录&&输出二维码到终端
        new DefaultWechatBot(Config.me().autoLogin(true).showTerminal(true))
                .registerListener(new WeChatEventHandler(this))
                .start();
    }

    static class DefaultWechatBot extends WeChatBot {
        private WeChatEventHandler eventHandler;

        public DefaultWechatBot(Config config) {
            super(config);
        }

        /**
         * 控制ChatGpt只接受文本消息
         */
        @Bind(msgType = MsgType.TEXT)
        public void onMessage(WeChatMessage chatMessage) {
            try {
                String response = eventHandler.doHandle(chatMessage);
                this.sendMsg(chatMessage.getFromUserName(), response);
            } catch (WeChatException ex) {
                log.error("wechat chatGpt send message failed.", ex);
            }
        }

        public WeChatBot registerListener(WeChatEventHandler handler) {
            this.eventHandler = handler;
            return this;
        }
    }
}
