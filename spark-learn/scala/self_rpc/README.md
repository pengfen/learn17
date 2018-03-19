自定义RPC

1. RPC通信框架(AKKA)
2. 定义2个类Master Worker

通信业务逻辑
首先启动Master 然后启动所有的Worker
1. Worker启动后 在preStart方法中与Master建立连接　向Master发送注册　将Worker的信息通过case class封装起来发送给Master
2. Master接受到Worker的注册消息后将Worker的信息保存起来　然后向Worker反馈注册成功
3. Worker定期向Master发送心跳
4. Master会定时清理超时的Worker

AKKA (http://akka.io)

1. intro.html

<!-- akka 依赖 -->
<dependency>
  <groupId>com.typesafe.akka</groupId>
  <artifactId>akka-actor_2.11</artifactId>
  <version>2.3.14</version>
</dependency>

<dependency>
  <groupId>com.typesafe.akka</groupId>
  <artifactId>akka-remote_2.11</artifactId>
  <version>2.3.14</version>
</dependency>

1. preStart() 该方法在Actor对象构造方法执行后执行 整个Actor生命周期中仅执行一次

2. receive() 该方法在Actor的preStart方法的执行完成后执行 用于接收消息 会被反复执行

3. 编写 Master 类

4. 编写 Worker 类

启动 Master (查看控制台) ---> 启动 Worker (查看控制台) ---> 停止 Worker ---> 查看Master的控制台