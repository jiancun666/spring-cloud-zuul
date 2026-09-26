## 2026-09-26 启动类与网关功能

- 状态：已完成
- 已确认：项目需要两个可运行的 Spring Boot 启动入口；网关需要将 `/api/user/**` 动态转发到 8080 下游。
- 已完成：将两个入口类整理为 `TestApplication` 和 `ZuulApplication`；补充控制器的 `@RestController`；将网关从固定 `/hello` 转发改为动态路径转发，并保留查询参数；更正网关控制器文件拼写。
- 已验证：源码检查确认两个入口均调用 `SpringApplication.run`，路由和端口配置与实现一致；已完成 Java 21 离线源码编译。30 秒内执行 `mvn -version` 失败，当前环境未安装 Maven，未执行完整 Maven 构建。

## 2026-09-26 agent_memory 文档整理

- 状态：已完成
- 已确认：三个记忆文件需要分别遵循项目上下文、任务进度和问题风险模板。
- 已完成：按二级标题和无序列表重排 `context.md`；按日期、状态、已确认、已完成、已验证字段重排 `progress.md`。
- 已验证：已检查文件内容为 UTF-8，结构字段齐全；30 秒内执行 `mvn -version` 失败，尚未执行 Maven 构建。

## 2026-09-26 增加 POST JSON 转发

- 状态：已完成
- 已确认：网关需要处理 POST `/api/user/**`，将 JSON 请求体转发到 8080，并保留路径、查询参数和 `Content-Type`。
- 已完成：在 `GetWayController` 中增加 `@PostMapping`；使用 POST 请求转发请求体；复用目标 URL 构造逻辑；返回下游状态码和响应体。
- 已验证：使用项目 JDK 21 和本地缓存依赖对 `GetWayController.java` 执行离线编译，`javac` 退出码为 0；尚未执行运行时集成测试。

## 2026-09-26 增加下游 POST 与 DELETE 测试接口

- 状态：已完成
- 已确认：需要在 `test` 控制器中提供 POST 和 DELETE 方法，用于验证下游接口及网关转发行为。
- 已完成：新增 POST `/post`，原样返回 JSON 请求体；新增 DELETE `/delete/{id}`，返回被删除 ID。
- 已验证：使用项目 JDK 21 和本地缓存依赖对 `test` 控制器执行离线编译，`javac` 退出码为 0；尚未执行运行时 HTTP 验证。

## 2026-09-26 增加 DELETE 网关转发

- 状态：已完成
- 已确认：网关需要将 DELETE `/api/user/**` 转发到 8080 对应路径，并保留查询参数。
- 已完成：在 `GetWayController` 中增加 `@DeleteMapping`，使用 Java `HttpClient.DELETE()` 转发请求，并返回下游状态码和响应体。
- 已验证：使用项目 JDK 21 和本地缓存依赖对网关控制器执行离线编译，`javac` 退出码为 0；关键映射、DELETE 方法、目标端口和查询参数逻辑检查通过；尚未执行运行时 HTTP 验证。

## 2026-09-26 修复 DELETE 测试接口 500

- 状态：待验证
- 已确认：`DELETE /delete/123` 返回 500；`@PathVariable String id` 依赖编译器保留参数名，运行时可能无法绑定路径变量。
- 已完成：改为显式绑定 `@PathVariable("id") String id`。
- 已验证：使用项目 JDK 21 离线编译通过；当前已运行的 8080 服务仍返回 500，需要重启 `TestApplication` 后重新发送请求。
