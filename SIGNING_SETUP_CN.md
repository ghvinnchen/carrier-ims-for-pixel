# CarrierIMS 编译与签名说明

## Debug APK（推荐先使用）

无需配置签名。Android Studio 会自动使用标准 Debug Key：

```bat
gradlew.bat :app:assembleDebug
```

输出位置：

```text
app\build\outputs\apk\debug\app-debug.apk
```

## 自定义签名

在项目根目录已有的 `local.properties` 中追加以下四项：

```properties
SIGN_KEY_STORE_FILE=D:/AndroidKeys/carrierims.jks
SIGN_KEY_STORE_PASSWORD=你的store密码
SIGN_KEY_ALIAS=carrierims
SIGN_KEY_PASSWORD=你的key密码
```

推荐在 Windows 路径中使用 `/`，避免反斜杠转义错误。

配置完整且密钥文件存在时，Debug 和 Release 都会使用该自定义签名；未配置时，Debug 自动使用标准 Debug Key，Release 输出未签名构建，不会在 Gradle Sync 阶段失败。

## 修复内容

- 避免缺少 `SIGN_KEY_STORE_FILE` 时执行 `file(null)`。
- 检查四项签名参数是否完整。
- 检查 `.jks` 文件是否真实存在。
- 没有 `.git` 目录时，版本号自动回退为本地版本，源码 ZIP 仍可编译。
- 保留 `signing.gradle` 作为兼容占位文件，但不再从 App 模块应用旧逻辑。
