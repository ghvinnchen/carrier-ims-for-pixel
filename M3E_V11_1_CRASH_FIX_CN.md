# Carrier IMS M3E V11.1 Crash Fix

## 闪退原因

V11 在 Compose `painterResource()` 中加载 `R.mipmap.ic_launcher`。
在 Android 8.0 及以上，该资源会解析为 Adaptive Icon XML，
而 Compose `painterResource()` 不能直接加载 Adaptive Icon XML，
因此进入包含 BrandHeader 的页面时会发生运行时异常并闪退。

## 修复内容

- 新增普通位图资源：

```text
app/src/main/res/drawable-nodpi/ic_launcher_brand.png
```

- BrandHeader 改为加载：

```kotlin
painterResource(R.drawable.ic_launcher_brand)
```

- Launcher 图标仍继续使用原有 Adaptive Icon；
- V11 的 Carrier Features 图标映射和其他功能保持不变。
