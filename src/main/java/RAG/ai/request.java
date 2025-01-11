package RAG.ai;
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class request {
    public static void main(String[] args) {
        System.out.println("----- start your input -----");

        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        String apiKey = "d3699795-0956-4f05-9651-cc1cd6a7106f";
        ArkService service = ArkService.builder().apiKey(apiKey).baseUrl("https://ark.cn-beijing.volces.com/api/v3/").build();

        System.out.println("\n----- standard request -----");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是一个智能小助手，你需要根据资料和你的知识，尽量以100字以内来简要回答用户的问题.资料: 挂号流程:1. 挂号前准备\n" +
                "确定科室：根据自己的症状确定挂号的科室（如内科、外科、皮肤科、妇科等）。如果不确定，可以在挂号窗口或导医台咨询。" +
                "携带证件：通常需要携带身份证、医保卡（如果适用）以及其他相关证件。" +
                "准备挂号费：挂号可能需要一定的费用，不同科室和专家挂号费会有所不同。" +
                "2. 选择挂号方式" +
                "方式一：现场挂号" +
                "到挂号窗口或自助机挂号：" +
                "到医院的挂号窗口或者自助挂号机处排队。" +
                "告知工作人员或选择自助机上想挂的科室和医生。" +
                "支付挂号费（可以是现金、银行卡或移动支付）。" +
                "获取挂号单，确认就诊科室和排队号码。" +
                "去导医台咨询（如果不清楚流程）：" +
                "导医台通常会引导患者选择合适的科室，避免挂错号。" +
                "方式二：线上挂号" +
                "通过医院官网或App挂号：" +
                "注册并登录医院官网或其官方App。" +
                "选择“挂号”功能，搜索科室或医生。" +
                "查看医生的出诊时间，并选择合适的时间段。" +
                "确认预约后支付挂号费用，生成挂号信息。" +
                "通过第三方平台挂号：" +
                "例如，国内常用的挂号平台有“支付宝”、“微信健康”、“健康码”或其他医疗服务平台。" +
                "搜索医院和科室，完成挂号流程。" +
                "3. 就诊流程" +
                "到医院签到：" +
                "如果挂的是线上号，通常需要在就诊前到医院通过自助机或导医台签到。" +
                "如果是现场挂号，拿着挂号单直接去相应的科室候诊。" +
                "前往科室排队：" +
                "挂号单上会显示医生的诊室号和排队号。" +
                "到达指定科室后，在候诊区等待，按照叫号系统依次就诊。" +
                "医生问诊：" +
                "进入诊室后，向医生详细描述症状。" +
                "医生可能会开检查单或直接开药。" +
                "4. 后续流程" +
                "缴费：" +
                "如果需要做检查或治疗，拿着医生开的单据到收费处缴费（自助机或人工窗口）。" +
                "一些医院支持通过手机App支付。" +
                "检查或治疗：" +
                "按指引到相应的科室做检查，如化验、CT、B超等。" +
                "检查完毕后等待结果（可能当日领取或延后）。" +
                "取药或复诊：" +
                "如果医生开了药，可以拿着处方单到药房领取药物。" +
                "如果需要复诊，记得医生建议的复诊时间。").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(request).build();
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("ep-20241226144815-mgmzx")//这个写你的ep-开头的id
                .messages(messages)
                .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> System.out.println(choice.getMessage().getContent()));

        // shutdown service
        service.shutdownExecutor();
    }

}


