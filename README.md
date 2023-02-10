# showcase-chatgpt-robot
基于ChatGPT实现的QQ/WeChat机器人，目前只支持QQ

项目依赖三方组件库
* mirai [高效率 QQ 机器人支持库](https://github.com/mamoe/mirai)

* openai-java [基于 OpenAI 的 GPT-3 api 的 Java 库](https://github.com/TheoKanning/openai-java)

### 准备工作
 
#### 注册ChatGPT账号(略)
参考：https://www.bilibili.com/read/cv20802226/


#### 项目中配置ChatGPT相关属性

> application.properties

```properties
# chatgpt secret key
chatgpt.apikey=sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
chatgpt.completion.model=text-davinci-003
chatgpt.completion.max-tokens=2048

# mirai bot
chatgpt.mirai.protocol=IPAD
chatgpt.mirai.heartbeat-strategy=STAT_HB
chatgpt.mirai.device-file-name=derives.json
chatgpt.mirai.working-dir=D:/dist

# qq
chatgpt.qq.number=8888888888
chatgpt.qq.password=123456
```

1、chatgpt.apikey配置ChatGPT账号对应的secretKey

2、若应用部署在Linux系统，注意修改`chatgpt.mirai.working-dir`的目录位置

3、如果启动过程中提示<b>QQ版本过低，请升级至最新版本后再登录</b>，则可以尝试修改`chatgpt.mirai.protocol`为其它协议

4、通过修改`chatgpt.completion.max-tokens`来控制交互数据的字符数(问题和答案的总和)

> Maven dependency

```xml
<properties>
    <!-- 重定义kotlinx-coroutines-bom中的依赖的版本 -->
    <kotlin-coroutines.version>1.6.4</kotlin-coroutines.version>
</properties>
<dependencies>
    <dependency>
        <groupId>com.theokanning.openai-gpt3-java</groupId>
        <artifactId>client</artifactId>
        <version>0.9.0</version>
    </dependency>
    <dependency>
        <groupId>net.mamoe</groupId>
        <artifactId>mirai-core-jvm</artifactId>
        <version>2.13.3</version>
    </dependency>
    ...
</dependencies>
```

注意：mirai的版本必须是2.13.3+，以前的版本是通过QQ扫码授权，但是启动过程中一直显示`net.mamoe.mirai.internal.utils.crypto.DecryptionFailedException`异常，从2.13.3+版本开始QQ授权调整为短信认证的方式

#### 效果图
![image](https://user-images.githubusercontent.com/22070521/218131116-34828647-18c6-4afc-80f1-addc953311df.png)


#### ChatGPT集成微信
(待续)
