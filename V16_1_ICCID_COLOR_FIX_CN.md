# Carrier IMS M3E V16.1 — ICCID Color Fix

修复 Extra Tab 中 ICCID 状态 Chip 仍显示浅黄色的问题。

修改后：
- ICCID 使用 `surfaceContainerHighest`；
- 视觉上与 MCC/MNC、ISO 信息块保持同一中性色系；
- 其他正向状态 Chip 仍保留原有浅黄色/强调色，不受影响。
