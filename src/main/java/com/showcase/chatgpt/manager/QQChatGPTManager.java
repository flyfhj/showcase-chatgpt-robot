package com.showcase.chatgpt.manager;

import com.showcase.chatgpt.config.ChatGPTProperties;
import com.showcase.chatgpt.config.ChatGPTProperties.Mirai;
import com.showcase.chatgpt.config.ChatGPTProperties.QQ;
import com.showcase.chatgpt.manager.event.QQEventHandler;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author fuhuijun
 * @date 2023/2/8 13:42
 */
public class QQChatGPTManager extends ChatGPTManager {
    private final QQ qq;
    private final Mirai mirai;

    private Bot bot;

    public QQChatGPTManager(ChatGPTProperties properties) {
        super(properties);
        this.qq = properties.getQq();
        this.mirai = properties.getMirai();
    }

    @PostConstruct
    public void init() {
        QQChatGPTManager.this.newBotInstance(BotConfiguration.getDefault(), this.mirai)
            .registerListener(new QQEventHandler(this))
            .start();
    }

    private QQChatGPTManager newBotInstance(BotConfiguration configuration, ChatGPTProperties.Mirai mirai) {
        configuration.setWorkingDir(new File(mirai.getWorkingDir()));
        configuration.fileBasedDeviceInfo(mirai.getDeviceFileName());
        configuration.setProtocol(mirai.getProtocol());
        configuration.setHeartbeatStrategy(mirai.getHeartbeatStrategy());
        // new bot
        this.bot = BotFactory.INSTANCE.newBot(this.qq.getNumber(), this.qq.getPassword(), configuration);
        return this;
    }

    /**
     * qq登录
     */
    private QQChatGPTManager start() {
        bot.login();
        return this;
    }

    /**
     * qq事件订阅
     */
    private QQChatGPTManager registerListener(SimpleListenerHost listenerHost) {
        bot.getEventChannel().registerListenerHost(listenerHost);
        return this;
    }
}
