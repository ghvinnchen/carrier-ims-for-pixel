# Carrier IMS M3E V12 Stable

V12 是橙色 SIM 图标与 M3E UI 的稳定整合版本。

## App Icon 最终调整

- 橙色 SIM 主体由 V11.2 的约 56% 缩小到约 48%；
- 增加四周留白，使视觉比例更接近 Pixel Camera、My Pixel 与 Gemini；
- 主体轻微下移，修正视觉重心；
- 橙色饱和度略微降低，减轻 Launcher 中的视觉膨胀；
- 背景继续采用浅米黄色圆形 Tonal Surface；
- 同步更新 Adaptive、Round、Legacy、Monochrome、Play Store 与 App 内品牌图标。

## 稳定性

- 保留 `ic_launcher_brand.png`，避免 Compose 直接加载 Adaptive Icon XML 导致闪退；
- 保留 V11.1 对 Carrier Features 图标背景的统一修复；
- VoWiFi、Cross SIM Calling 等关闭状态下仍显示清晰的 Tonal 图标背景；
- 保留 V11 的 5G、5G+、4G、LTE 等差异化功能图标。

## 使用建议

安装前先卸载旧版 App，再重新安装，以清除 Pixel Launcher 图标缓存。
