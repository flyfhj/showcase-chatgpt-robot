import com.showcase.chatgpt.manager.ChatGPTManager;
import org.junit.Test;

/**
 * @author fuhuijun
 * @date 2023/2/7 11:10
 */
public class ChatGPTDemo {

    @Test
    public void completion() {
        ChatGPTManager manager = new ChatGPTManager("sk-xxxxxxxxxxxxxx");
        String result = manager.createCompletion("今天是星期几?");
        System.out.println("结果是===>" + result);
    }
}
