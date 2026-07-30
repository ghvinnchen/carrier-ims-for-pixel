# Carrier IMS M3E Edition V5.1

本版是在 V5 Build Fix 基础上的正式迭代版。

## 修复与增强

- 保留上一版对 Kotlin 多余右花括号的修复；
- 在 `app/build.gradle.kts` 中正式加入 Compose Animation 依赖：
  `androidx.compose.animation:animation`；
- 恢复 Hero Card、About Card 和 Features Card 的 `animateContentSize()`；
- 状态变化时卡片尺寸过渡更加自然；
- 保留 Hero Card、56dp 按钮、About 信息层级、Support 头像布局；
- 保留 Material You 动态配色、M3E 圆角体系和方案 A App Icon；
- 不包含 `.git`、`.gradle`、`.idea`、`build` 及本机 `local.properties`。

## 编译建议

覆盖后先执行 Gradle Sync，再执行 Clean Project 和 Build APK(s)。
