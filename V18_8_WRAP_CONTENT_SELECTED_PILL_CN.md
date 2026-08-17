# V18.8 Wrap-Content Selected Pill Fix

上一版的问题是背景仍然画在整个 weighted Box 上，所以 IMS/About 视觉上依旧过宽。

本版改为：
- 外层 Box 固定 weight(1f)，只负责均匀点击区域；
- 选中背景移到内层 Row；
- 内层 Row 使用 wrapContentWidth()；
- 胶囊宽度 = Icon + 文本 + 左右 14dp padding；
- IMS / About 会明显变窄；
- Connectivity 会自然变宽以完整容纳文字；
- Floating Bar 总长度与三项点击区域保持不变。
