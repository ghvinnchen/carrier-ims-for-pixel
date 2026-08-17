# V18.8 Wrap-Content Selected Pill — Compile Fix

上一版生成 ZIP 时错误地把新 Tab 代码插入后，又残留了一段旧 Tab modifier，
导致 MainActivity.kt 约 1014 行出现重复语句并引发连锁语法错误。

本版重新从干净的：
CarrierIMS_M3E_V18_8_AdaptiveSelectedPillWidth_Source.zip
开始修改，而不是在损坏版上打补丁。

实现：
- 三个 Tab 的点击区域保持 `.weight(1f)` 等宽；
- 选中状态的可见胶囊使用 `wrapContentWidth()`；
- IMS / About 胶囊按 Icon + 文本实际宽度显示；
- Connectivity 会自然更宽；
- Google Photos Collections 风格图标保留；
- Floating Bar 尺寸、位置、Material 配色、Overlay 行为不变。
