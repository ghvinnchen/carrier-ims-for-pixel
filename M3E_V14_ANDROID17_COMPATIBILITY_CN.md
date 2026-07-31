# Carrier IMS M3E V14 — Android 17 Compatibility Edition

本版本严格基于：
`CarrierIMS_M3E_V13_VectorIcon_Source(2).zip`

## 核心修复

新增统一兼容层：

```text
app/src/main/java/io/github/vvb2060/ims/privileged/
ShellPermissionDelegateCompat.kt
```

所有 privileged Instrumentation 不再直接调用：

- `startDelegateShellPermissionIdentity()`
- `stopDelegateShellPermissionIdentity()`

而是通过反射检查运行时接口。Android 17 缺少隐藏接口时，
不会再产生 `NoSuchMethodError`，并继续使用 Shizuku
Instrumentation 当前已有的 shell 身份。

## 已修复文件

- ConfigReader.kt
- SimReader.kt
- ImsStatusReader.kt
- CaptivePortalFixer.kt
- ImsModifier.kt
- ApnModifier.kt
- BrokerInstrumentation.kt
- ImsResetter.kt

日志中明确崩溃的 `ConfigReader.kt` 已包含在本次修复内。

## 图标与 UI

未修改任何 V13 Launcher Icon、App 内品牌图标、Compose UI 或图片资源。
图标文件已通过 SHA-256 校验确认保持不变。

## 建议测试

1. Show LTE as 4G
2. IMS Registration
3. VoLTE
4. VoWiFi
5. 返回页面后确认状态能否重新读取

Logcat 中不应再出现：
`No interface method stopDelegateShellPermissionIdentity()`。
