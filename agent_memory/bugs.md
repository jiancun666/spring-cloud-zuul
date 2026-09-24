已修复：`test/src/main/java/org/example/controller/test.java` 补充 `@RestController`，解决 `/hello` 返回 404 的问题。
待处理：`test` 模块当前只提供 `/hello`，尚无 `/test`；网关固定转发会保留下游 404。`zuul/application.yml` 中的 `/api/users/**` 配置尚无读取逻辑，本次未动。
