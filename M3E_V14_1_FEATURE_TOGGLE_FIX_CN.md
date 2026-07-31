# Carrier IMS M3E V14.1 — Feature Toggle Fix

本版本基于 V14 Android 17 Compatibility Edition。

## 修复问题

V14 中部分 Feature 使用以下逻辑：

```kotlin
if (enabled) {
    bundle.putBoolean(KEY, true)
}
```

这意味着打开时会写入 `true`，但关闭时不会写入 `false`，
因此会出现“可以打开，但关闭后状态不恢复”的问题。

## V14.1 修改

所有匹配的布尔 Feature 改为显式写入当前开关值：

```kotlin
bundle.putBoolean(
    KEY,
    enabled,
)
```

因此：

- 打开开关时写入 `true`
- 关闭开关时写入 `false`

`Show LTE as 4G` 关闭后会明确写入 `false`，状态栏才有机会恢复显示 LTE。

## 注意

CarrierConfig 写入后，状态栏图标可能不会立即刷新。可尝试：

1. 开关一次飞行模式；
2. 关闭并重新启用 SIM；
3. 极少数情况下重启手机。

## 保留内容

- 保留 V14 的 Android 17 hidden API 兼容修复；
- 保留 V13 App Icon 与 UI；
- 未修改任何图标资源或 Compose 界面。
