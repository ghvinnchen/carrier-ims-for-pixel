# Carrier IMS M3E V18 — Remove Support/Partners Tabs (Build Fix)

基于 CarrierIMS_M3E_V17_Branding_Source(2).zip。

修改：
- Bottom Navigation 仅保留 IMS、Extra、About；
- 移除 Support 与 Partners 页面入口；
- 保留 Support 相关 model、类型与后端代码，避免 MainActivity 中现有类型引用编译失败；
- 修复上一版误删 SupportRecord、SupportRules、SupportPaymentChannel imports 导致的 Unresolved reference；
- About 页面保持 SystemInfoCard，不会误显示 CooperationPage；
- 版本号更新为 18.0。
