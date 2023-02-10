package com.showcase.chatgpt.config;

import lombok.Data;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fuhuijun
 * @date 2023/2/7 19:02
 */
@Data
@ConfigurationProperties(prefix = "chatgpt")
public class ChatGPTProperties {

    /**
     * chatGpet secret key
     */
    private String apiKey;

    /**
     * qq entity
     */
    private QQ qq;

    /**
     * mirai entity
     */
    private Mirai mirai;

    /**
     * completion entity
     */
    private Completion completion;

    /**
     * QQ properties
     */
    @Data
    public static class QQ {
        private long number;
        private String password;
    }

    /**
     * Mirai properties
     */
    @Data
    public static class Mirai {

        /**
         * 机器人协议
         */
        private BotConfiguration.MiraiProtocol protocol = BotConfiguration.MiraiProtocol.ANDROID_PHONE;

        /**
         * 心跳检测策略
         */
        private BotConfiguration.HeartbeatStrategy heartbeatStrategy = BotConfiguration.HeartbeatStrategy.STAT_HB;

        /**
         * 设备信息文件名
         */
        private String deviceFileName = "derives.json";

        /**
         * mirai工作目录
         */
        private String workingDir = "/data/mirai";
    }

    @Data
    public static class Completion {
        /**
         * GPT计算模型
         */
        private String model;

        /**
         * 传输字符最大值
         */
        private int maxTokens;
    }
}
