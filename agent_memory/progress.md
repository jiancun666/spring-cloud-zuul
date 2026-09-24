已确认需求：将 `test` 模块的 `org.example.Main` 改为 Spring Boot 启动类，使用 `SpringApplication.run`。
已完成入口类修改与静态检查；因当前环境未安装 `mvn`，未能执行 Maven 编译。
已定位 `/hello` 返回 404 的原因是控制器类缺少 `@RestController`，已补充该注解。
