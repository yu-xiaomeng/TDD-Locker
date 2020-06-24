## 需求

储物柜（locker）可以存包、取包

## Tasking

- Given Locker有可用容量，When Locker存包，Then 存包成功，返回票据
- Given Locker已满，When Locker存包，Then 存包失败，提示储物柜已满
- Given 一张有效票据，When Locker取包，Then 取包成功
- Given 一张伪造票据，When Locker取包，Then 取包失败，提示非法票据
- Given 一张重复使用的票据，When Locker取包，Then 取包失败，提示非法票据

## Primary robot Tasking
- Given robot管理2个Locker，两个Locker都有可用容量，When robot存包，Then 包成功存入Locker1，返回票据
- Given robot管理2个Locker，Locker1已存满，Locker2有可用容量，When robot存包，Then 包成功存入Locker2，返回票据
- Given robot管理2个Locker，所有Locker都已存满，When robot存包，Then 存包失败，提示储物柜已满
- Given robot管理2个Locker，一张有效票据，When robot取包，Then 取包成功
- Given robot管理2个Locker，一张伪造票据，When robot取包，Then 取包失败，提示非法票据

## Smart robot Tasking
- Given smart robot管理2个Locker，Locker1可用容量2，Locker2可用容量1 When smart robot存包 Then 成功存入Locker1，返回票据
- Given smart robot管理2个Locker，Locker1可用容量1，Locker2可用容量2 When smart robot存包 Then 成功存入Locker2，返回票据
- Given smart robot管理2个Locker，两个可用容量都为2 When smart robot存包 Then 成功存入Locker1，返回票据
- Given smart robot管理2个Locker，两个已满 When smart robot存包 Then 存包失败，提示储物柜已满
- Given smart robot管理2个Locker，一张有效票据 When smart robot取包 Then 取包成功
- Given smart robot管理2个Locker，一张无效票据 When smart robot取包 Then 取包失败，提示无效票据
- Given smart robot、primary robot同时管理2个locker，一张primary robot存包有效票据 Then smart robot取包 Then 取包成功
- Given smart robot、primary robot同时管理2个Locker，一张smart robot存包有效票据 Then primary robot取包 Then 取包成功

## Locker Robot Manager
- Given locker robot manager管理两个robot（robot1、robot2），robot1管理（locker1、locker2），robot2管理（locker2、locker3） when locker robot manager管理locker1 then 报错
- Given locker robot manager没有管理robot，管理两个locker，两个locker都有容量 when locker robot manager存包 then 成功存入locker1，返回ticket
- Given locker robot manager没有管理robot，管理两个locker（locker1、locker2），locker2已满，locker1有容量 when locker robot manager存包 then 成功存入locker1，返回ticket
- Given locker robot manager没有管理robot，管理两个locker（locker1、locker2），locker1已满，locker2有容量 when locker robot manager存包 then 成功存入locker2，返回ticket
- Given locker robot manager没有管理robot，管理两个locker，两个locker已满 when locker robot manager存包 then 存包失败，提示储物柜已满
- Given locker robot manager管理两个robot，两个robot管理locker都有容量 when locker robot manager存包 then 存包成功，返回ticket
- Given locker robot manager管理两个robot（robot1、robot2），robot1有容量，robot2已满 when locker robot manager存包 then 成功存入robot1，返回ticket
- Given locker robot manager管理两个robot（robot1、robot2），robot2有容量，robot1已满 when locker robot manager存包 then 成功存入robot2，返回ticket
- Given locker robot manager管理两个robot（robot1、robot2），两个robot都满 when locker robot manager存包 then 存包失败，提示储物柜已满
- Given locker robot manager管理两个机器人（robot1、robot2），一个locker，两个robot已满，locker有容量 when locker robot manager存包 then 成功存入locker，返回ticket
- Given locker robot manager管理两个机器人（robot1、robot2），一个locker，两个robot已满，locker已满 when locker robot manager存包 then 存包失败，提示储物柜已满
- Given locker robot manager管理两个机器人，一个locker，一张有效locker robot manager票据 when locker robot manager取包 then 取包成功
- Given locker robot manager管理两个机器人，一个locker，一张无效locker robot manager票据 when locker robot manager取包 then 取包失败，提示无效票据
