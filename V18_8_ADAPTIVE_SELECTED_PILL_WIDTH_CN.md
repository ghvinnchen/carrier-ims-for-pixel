# V18.8 Adaptive Selected Pill Width

基于 V18.8 Connectivity Pill Width Fix。

问题：
- Connectivity 文字较长，需要更宽的选中胶囊；
- IMS / About 文字较短，继续使用 1.4 weight 会显得过宽。

修复：
- 未选中 Tab：0.9f
- Connectivity 选中：1.35f
- IMS / About 选中：1.15f

这样：
- Connectivity 仍有足够空间完整显示 icon + 文本；
- IMS / About 的选中胶囊明显收窄，更接近 Google Photos 的按内容适配感；
- Floating Bar 总体尺寸、位置、Overlay 行为不变。
