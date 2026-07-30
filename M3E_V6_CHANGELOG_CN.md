# Carrier IMS M3E Pixel Edition V6

V6 在 V5.1 的稳定版本上进行整体 UI 优化，重点是 Dashboard 化、统一状态语言和 Pixel 原生应用的布局细节。

## 本次升级

- Extra 页面 Region Compatibility 改为四块 Dashboard Metric Tile；
- APN 卡片改用信息卡 + 状态 Chip，两个操作按钮等宽且统一为 56dp；
- Support 表单改用 Filled TextField，减少传统 Outlined Form 的工具感；
- Alipay / WeChat Pay 改用 Filled Tonal Button；
- Support Messages 使用更宽松的 30dp 容器和 22dp 内边距；
- IMS Feature 行不再使用 I / V / 5 等占位首字母，统一改为 Material Rounded Icon；
- 页面滚动内容增加底部安全留白，避免被 Bottom Navigation 遮挡；
- 新增 `ui/components/V6Components.kt`，集中维护 Metric Tile 和 Status Chip；
- 保留 V5.1 的 Hero Card、Material You、M3E Shapes、Animation 和方案 A App Icon。

## 代码结构

新增：

`app/src/main/java/io/github/vvb2060/ims/ui/components/V6Components.kt`

后续可继续把 About、IMS、Extra 和 Support 页面逐步拆分为独立文件。
