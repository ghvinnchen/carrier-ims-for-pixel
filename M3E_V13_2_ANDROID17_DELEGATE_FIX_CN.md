# Carrier IMS M3E V13.2 — Android 17 Delegate API Fix

本版本严格基于用户重新上传的
`CarrierIMS_M3E_V13_VectorIcon_Source(2).zip` 修改。

## 修复内容

`ImsModifier.kt` 不再直接链接：

- `startDelegateShellPermissionIdentity()`
- `stopDelegateShellPermissionIdentity()`

而是通过运行时反射查找和调用。Android 17 缺少隐藏接口时：

- 不再抛出 `NoSuchMethodError`；
- 不让 stop 清理错误覆盖 CarrierConfig 写入结果；
- 尝试继续使用当前 Shizuku Instrumentation 身份执行配置。

同时将 `SimReader.kt` 中剩余的直接 stop 调用改为反射调用，
避免刷新 SIM 信息时复现同类错误。

## 图标与 UI

本版本未修改任何 launcher icon、brand icon、Compose UI 或其他图片资源，
完整保留上传的 V13 图标与界面。
