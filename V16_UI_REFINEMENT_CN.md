# Carrier IMS M3E V16 — UI Refinement

基于 V15.1 Boolean Toggle Complete Fix。

## IMS Tab
- 顶部 Carrier IMS 大卡片改为与 SIM Card 相同的中性 Surface 背景；
- 不缩窄卡片宽度，保留与页面其它卡片一致的 16dp 横向边距；
- 减少内部上下留白、组件间距、图标尺寸和 Refresh 高度；
- 解决的重点是“偏高”，而不是“过宽”；
- 副标题缩短为 “Carrier & IMS Toolkit”。

## Extra Tab
- Region Compatibility 中 Mainland SIM、TikTok Fix 的黄色强调块，
  改为与 MCC/MNC 相同的中性背景；
- ICCID 信息块同步改为中性背景。

## About Tab
- Runtime / Shizuku Ready 改为与 Patch 相同的背景色；
- 保留 Refresh 按钮作为主要绿色操作入口。

## 保留
- V15 Profile Tools；
- V15.1 VoLTE、VoWiFi、VoNR、Cross SIM 双向开关修复；
- Android 17 兼容修复；
- 原有图标资源。
