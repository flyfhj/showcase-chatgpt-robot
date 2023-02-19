package com.showcase.chatgpt.manager.event;

import com.showcase.chatgpt.manager.ChatGPTManager;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 自定义事件监听器
 *
 * @author fuhuijun
 * @date 2023/2/7 17:00
 */
@Slf4j
public class QQEventHandler extends SimpleListenerHost {
    private final ChatGPTManager chatGPTManager;

    public QQEventHandler(ChatGPTManager chatGPTManager) {
        this.chatGPTManager = chatGPTManager;
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("Event handle Exception，Caused by：", exception);
    }

    /**
     * 处理群组@机器人消息
     *
     * @param event
     * @return
     */
    @EventHandler
    public ListeningStatus onMessage(GroupMessageEvent event) {
        String message = event.getMessage().contentToString();
        log.info("[群组]-接收到 [{}] 的消息：{}", event.getSenderName(), message);

        //invoke chatGpt server
        chatGPTManager.createCompletion(message);
        return ListeningStatus.LISTENING;
    }

    /**
     * 处理单人消息
     *
     * @param event
     * @return
     */
    @EventHandler
    public ListeningStatus onMessage(FriendMessageEvent event) {
        String message = event.getMessage().contentToString();
        log.info("[好友]-接收到 [{}] 的消息：{}", event.getSenderName(), message);

        //invoke chatGpt server
        try {
            String completion = chatGPTManager.createCompletion(message);
            event.getSubject().sendMessage(completion);
        } catch (Exception ex) {
            log.error("qq chatGpt send message failed.", ex);
        }
        return ListeningStatus.LISTENING;
    }
}
