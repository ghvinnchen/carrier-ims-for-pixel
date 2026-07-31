# Carrier IMS M3E V16.5 — In-App Icon Mask Fix

问题：
IMS Tab 与 About Tab 的品牌图标虽然使用了 CircleShape，
但 PNG 自身仍保留了近白色外沿，因此视觉上出现白色多边形/方形边框。

修复：
- 重新生成 `ic_launcher_brand.png`；
- 对图标应用精确圆形 Alpha Mask；
- 完全移除圆形以外的白色和近白色像素；
- BrandHeader 改用 `ContentScale.Crop`；
- Launcher Icon 保持 V16.3 的安全区设置不变。

结果：
IMS Tab 与 About Tab 中只显示完整圆形图标，不再出现白色多边形外框。
