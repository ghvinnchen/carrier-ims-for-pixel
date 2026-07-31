# Carrier IMS M3E V16.4 — In-App Icon Consistency

修复 IMS Tab 和 About Tab 顶部品牌图标出现“方形套圆形”的问题。

修改：
- BrandHeader 图标容器由圆角方形改为 CircleShape；
- 移除额外浅色方形背景；
- App 内图标直接按圆形裁切并铺满容器；
- Launcher Icon 保持 V16.3 的安全区尺寸不变。

结果：
- IMS Tab、About Tab 与桌面 App Icon 的圆形外观一致；
- 不再出现白色或浅色方形底框。
