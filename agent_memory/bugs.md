## 2026-09-26 `/hello` 返回 404

- 状态：已修复
- 已确认：`test/src/main/java/org/example/controller/test.java` 缺少 `@RestController`，导致控制器未被 Spring MVC 注册。
- 已完成：为控制器补充 `@RestController`。
- 已验证：源码检查确认注解已存在；未执行运行时 HTTP 验证。

## 2026-09-26 网关路由范围与配置

- 状态：已缓解
- 已确认：网关当前实现 GET、POST 和 DELETE 转发，端口为 8081；PUT、PATCH 等其他网关方法仍未实现；`zuul/application.yml` 中的 `/api/users/**` 配置没有读取逻辑。
- 已完成：将网关固定 `/hello` 转发改为 `/api/user/**` 的动态剩余路径转发并保留查询参数；新增 POST JSON 请求体和 `Content-Type` 转发；新增下游 POST 和 DELETE 测试接口；新增 DELETE 网关转发。
- 已验证：源码检查确认路由实现和配置目标均为 `http://localhost:8080`；网关和下游测试控制器均已通过 JDK 21 离线编译；未执行运行时集成测试。

## 2026-09-26 Maven 构建环境

- 状态：待验证
- 已确认：在 30 秒超时内执行 `mvn -version` 失败，当前环境未安装 Maven，因此无法运行完整 Maven 构建。
- 已完成：保留 Java 21 离线源码编译作为已有验证方式。
- 已验证：`mvn -version` 已确认不可用；需要安装或提供 Maven 后补充验证。

## 2026-09-26 DELETE 测试接口返回 500

- 状态：待验证
- 已确认：`DELETE /delete/123` 返回 500；路径变量未显式指定名称，在未保留 Java 参数名的编译配置下可能导致 Spring 无法绑定 `{id}`。
- 已完成：改为 `@PathVariable("id") String id`，避免依赖编译器参数名元数据。
- 已验证：控制器已通过 JDK 21 离线编译；当前运行实例尚未重启，重启 `TestApplication` 后需重新验证 HTTP 响应。
