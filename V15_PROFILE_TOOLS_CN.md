# Carrier IMS M3E V15 — Profile Tools

位置：Extra / 附加 Tab

新增：
- Restore Recommended Profile：VoLTE、UT 开启，其余可选功能关闭；
- Export Current CarrierConfig：使用 Android 文件选择器导出 JSON；
- Import CarrierConfig：导入 JSON 并加入已保存 Profile；
- Save Profile / Load Profile：沿用并扩展原有 Config Backup 功能。

说明：
- 导入操作只保存 Profile，不会自动写入 SIM；
- 用户点击 Restore 后才会应用；
- 导出内容含 SIM、MCC/MNC、Country ISO、Feature 值和 Country MCC Override。
