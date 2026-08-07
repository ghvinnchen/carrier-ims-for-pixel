# Carrier IMS M3E V18.4 — Google Photos Overlay Navigation

基于 V18.3。

## 与 V18.3 的关键区别

V18.3 虽然 Bottom Bar 看起来悬浮，但仍使用 Scaffold `bottomBar`
生成的 `innerPadding`，所以内容区会在导航栏上方提前结束。
视觉上导航栏下面是一块独立的空白区域，这与 Google Photos 不同。

V18.4 改为真正 Overlay 行为：

- 不再把 Scaffold bottomBar 的 bottom innerPadding 应用给页面内容；
- IMS / Extra / About 内容可以连续滚动到悬浮栏背后；
- Floating Bar 始终悬浮在页面内容之上；
- 页面底部仍保留 112dp 可滚动安全空间；
- 因此滚动到列表末尾时，最后一张 Card 仍可以被拉到 Floating Bar 上方；
- 平时滚动过程中，内容会像 Google Photos 的照片一样从导航栏后方经过。

Bottom Bar 本身仍保持：
- 选中项：Icon + 名称；
- 未选中项：仅名称；
- 圆角 Pill 容器；
- 阴影；
- navigationBarsPadding。
