已确认需求：将 `test` 模块的 `org.example.Main` 改为 Spring Boot 启动类，使用 `SpringApplication.run`。
已完成入口类修改与静态检查；因当前环境未安装 `mvn`，未能执行 Maven 编译。
已定位 `/hello` 返回 404 的原因是控制器类缺少 `@RestController`，已补充该注解。
本次：将 `zuul` 入口改为 Spring Boot 启动类，实现固定 GET 转发并更正控制器文件拼写。
已通过 Java 21 离线源码编译；因环境未安装 Maven，未执行完整 Maven 构建。
本次：将两个启动类从 `Main` 分别更名为 `TestApplication` 和 `ZuulApplication`，保持启动行为不变。
验证：两个新入口类均通过 Java 21 离线编译；无旧类名的源码或配置引用。Maven 仍不可用，未运行完整构建。
本次：将网关 GET 路由从固定 `/api/user/test -> /hello` 改为动态 `/api/user/** -> /**`，目标服务仍为 8080，并保留查询参数。代码级断言通过；Maven 不可用，未执行完整构建。
