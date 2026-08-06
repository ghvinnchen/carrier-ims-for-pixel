# Carrier IMS M3E V18.2.1 — Weight Compile Fix

修复：
`Cannot access 'val RowColumnParentData?.weight'`

移除了错误的显式 import：

```kotlin
import androidx.compose.foundation.layout.weight
```

`.weight(1f)` 保留在 RowScope 中使用，无需显式导入。
底部导航效果保持不变：
- 选中项显示 Icon + 名称；
- 未选中项仅显示名称。
