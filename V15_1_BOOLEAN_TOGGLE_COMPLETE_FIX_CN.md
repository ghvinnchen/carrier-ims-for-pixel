# Carrier IMS M3E V15.1 — Boolean Toggle Complete Fix

修复以下功能关闭时未明确写入 false 的问题：

- VoLTE
- VoWiFi
- VoNR
- Cross SIM

关闭 VoLTE 后，CarrierConfig 会写入 VoLTE 不可用，并隐藏 Enhanced 4G/LTE 与 LTE+ 图标。
关闭 VoWiFi 后，会同步关闭 WFC 可用性、模式编辑项和状态栏 Wi-Fi Calling 图标。

应用后如通话能力未立即变化，请开关一次飞行模式或禁用再启用 SIM，
让电话与 IMS 服务重新加载 CarrierConfig。
