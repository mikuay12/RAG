package RAG.ai;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class advise {
    public static void main(String[] args) {
        System.out.println("----- start your input -----");

//        Scanner scanner = new Scanner(System.in);
//        String request = scanner.nextLine();
        String request = "";
        String apiKey = "d3699795-0956-4f05-9651-cc1cd6a7106f";
        ArkService service = ArkService.builder().apiKey(apiKey).baseUrl("https://ark.cn-beijing.volces.com/api/v3/").build();

        System.out.println("\n----- standard request -----");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是一个智能医疗小助手，你需要根据你的知识,随机给出'每日健康小知识',这个健康知识可以是生活方面的,也可以是医疗方面的,简短地用一句话陈述一个知识,句子前可以加上'你知道吗'").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(request).build();
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("ep-20241226144815-mgmzx")//这个写你的ep-开头的id
                .messages(messages)
                .temperature(0.9)
//                .stream(true)
                .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> System.out.println(choice.getMessage().getContent()));

        // shutdown service
        service.shutdownExecutor();
    }

}


