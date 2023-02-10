# showcase-chatgpt-robot
基于ChatGPT实现的QQ/WeChat机器人，目前只支持QQ

项目依赖三方组件库
* mirai [高效率 QQ 机器人支持库](https://github.com/mamoe/mirai)

* openai-java [用于使用 OpenAI 的 GPT-3 api 的 Java 库](https://github.com/TheoKanning/openai-java)

### 准备工作
 
#### 注册ChatGPT账号(略)
参考：https://www.bilibili.com/read/cv20802226/


#### 项目中配置ChatGPT相关属性

> application.properties

1、chatgpt.apikey配置ChatGPT账号对应的secretKey

2、应用部署在Linux系统，注意修改`chatgpt.mirai.working-dir`的目录位置

3、如果启动过程中提示<b>前QQ版本过低，请升级至最新版本后再登录</b>，则可以尝试修改`chatgpt.mirai.protocol`为其它协议

4、通过修改`chatgpt.completion.max-tokens`值来控制数据交互的字符数(包括问题和答案)

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

> pom.xml

注意mirai的版本必须是2.13.3+，以前的版本需要通过QQ扫码授权，但是启动过程中一直显示`net.mamoe.mirai.internal.utils.crypto.DecryptionFailedException`异常，从2.13.3+版本开始QQ授权改成了短信的方式

```xml
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
```

#### 效果图
![image](https://user-images.githubusercontent.com/22070521/218085799-f78c8793-40db-4c15-b75e-62fc9c8e4eff.png)


#### ChatGPT集成微信
开发中
