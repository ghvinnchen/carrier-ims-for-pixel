# Carrier IMS M3E V11.1 — Orange SIM Icon & Feature Background Fix

## 新 App Icon

- 使用用户提供的橙黄色 SIM 卡图形；
- 自动移除图片外围白色背景；
- 图形按有效边界裁切并居中；
- 前景约占安全画布 66%，避免 Pixel Launcher 裁切；
- 同步更新 Adaptive、Round、Legacy、Monochrome 和 Play Store 图标；
- 同步更新 App 内使用的 `ic_launcher_brand.png`；
- 保留 V11.1 对 Compose Adaptive Icon 闪退的修复。

## Carrier Features

- 所有功能图标统一使用 `secondaryContainer` 背景；
- VoWiFi 和 Cross SIM Calling 即使处于关闭状态也会显示清晰的 Tonal 背景；
- 图标和文字徽标统一使用 `onSecondaryContainer` 前景色；
- 其他功能与 UI 保持不变。

安装前建议卸载旧版本，再安装新 APK，以清除 Pixel Launcher 图标缓存。
