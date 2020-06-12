## 需求

储物柜（locker）可以存包、取包

## Tasking

- Given Locker有可用容量，When Locker存包，Then 存包成功，返回票据
- Given Locker已满，When Locker存包，Then 存包失败，提示储物柜已满
- Given 一张有效票据，When Locker取包，Then 取包成功
- Given 一张伪造票据，When Locker取包，Then 取包失败，提示非法票据
- Given 一张重复使用的票据，When Locker取包，Then 取包失败，提示非法票据
