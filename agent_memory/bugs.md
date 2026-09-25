已修复：`test/src/main/java/org/example/controller/test.java` 补充 `@RestController`，解决 `/hello` 返回 404 的问题。
已处理：网关不再固定转发 `/hello`，而是将 `/api/user/**` 的剩余路径转发到下游对应路径。`test` 模块当前只提供 `/hello`，访问其他下游路径仍可能返回 404。`zuul/application.yml` 中的 `/api/users/**` 配置尚无读取逻辑，本次未动。
注意：当前实现仍只转发 GET，且网关端口保持 8081；用户描述中的无端口 `http://localhost` 未据此改变端口配置。
