# AirGame-Auto-KeepInventory

> 该模组同时适用于服务端和客户端环境

死亡不掉落模组，安装之后服务器启动时会自动为所有世界设置游戏规则 KeepInventory = true

你可能会问，我自己手动设置一次不就可以了，为什么还要多装一个这个模组？

在五彩方块的**资源世界服务器**，主要是配合 `fabric-auto-restart` 使用的

1. fabric-auto-restart 会在每天 24 点关闭服务器
2. 启动脚本会在服务器关闭后自动重启服务器、并删除地图
3. 新生成的地图默认 KeepInventory 为 false
4. 该模组会在服务器启动完成时重新将 KeepInventory 设置为 true
