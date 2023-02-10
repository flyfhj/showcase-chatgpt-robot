package com.showcase.chatgpt.event;

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
public class CustomerEventHandler extends SimpleListenerHost {
    private final ChatGPTManager chatGPTManager;

    public CustomerEventHandler(ChatGPTManager chatGPTManager) {
        this.chatGPTManager = chatGPTManager;
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("Event handle Exception，Caused by：", exception);
    }

    @EventHandler
    public ListeningStatus onMessage(GroupMessageEvent event) {
        String message = event.getMessage().contentToString();
        log.info("[group] recv message：{}", message);

        //invoke chatGpt server
        chatGPTManager.createCompletion(message);
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onMessage(FriendMessageEvent event) {
        String message = event.getMessage().contentToString();
        log.info("[friend] recv message：{}", message);

        //invoke chatGpt server
        String completion = chatGPTManager.createCompletion(message);
        try {
            event.getSubject().sendMessage(completion);
        } catch (Exception ex) {
            log.error("chatGpt send message failed.", ex);
        }
        return ListeningStatus.LISTENING;
    }
}
