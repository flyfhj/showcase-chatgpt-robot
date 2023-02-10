package com.showcase.chatgpt.manager;

import com.showcase.chatgpt.config.ChatGPTProperties;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.edit.EditChoice;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import org.apache.commons.lang3.RegExUtils;

import java.time.Duration;
import java.util.List;

/**
 * @author fuhuijun
 * @date 2023/2/7 18:10
 */
public class ChatGPTManager {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(60);

    private final OpenAiService openAiService;

    private Duration timeout = DEFAULT_TIMEOUT;

    private ChatGPTProperties properties;

    public ChatGPTManager(String apiKey) {
        this.openAiService = new OpenAiService(apiKey, timeout);
    }

    public ChatGPTManager(ChatGPTProperties properties) {
        this.properties = properties;
        this.openAiService = new OpenAiService(properties.getApiKey(), timeout);
    }

    /**
     * simple interface.<br>
     * Creates a completion for the provided prompt and parameters
     */
    public String createCompletion(String message) {
        CompletionRequest completionRequest = this.buildCompletionRequest(message);
        return this.createCompletion(completionRequest);
    }

    /**
     * Creates a completion for the provided prompt and parameters
     */
    protected String createCompletion(CompletionRequest completionRequest) {
        StringBuilder response = new StringBuilder();

        CompletionResult completion = openAiService.createCompletion(completionRequest);
        List<CompletionChoice> choices = completion.getChoices();
        choices.forEach(choice -> response.append(choice.getText()));
        // 去掉响应文本开头的\n\n
        return RegExUtils.replaceFirst(response.toString(), "\n\n", "");
    }

    /**
     * Creates a new edit for the provided input, instruction, and parameters
     */
    public String createEdit(EditRequest editRequest) {
        StringBuilder response = new StringBuilder();

        EditResult edit = openAiService.createEdit(editRequest);
        List<EditChoice> choices = edit.getChoices();
        choices.forEach(choice -> response.append(choice.getText()));
        // 去掉响应文本开头的\n\n
        return RegExUtils.replaceFirst(response.toString(), "\n\n", "");
    }

    private CompletionRequest buildCompletionRequest(String message) {
        return CompletionRequest.builder().prompt(message)
                .model(this.properties.getCompletion().getModel())
                .maxTokens(this.properties.getCompletion().getMaxTokens())
                .build();
    }
}
