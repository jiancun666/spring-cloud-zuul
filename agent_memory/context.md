本项目是 Maven 多模块工程，包含 `test` 和 `zuul` 模块，父 POM 继承 Spring Boot Web 依赖。
网关模块 `zuul` 监听 8081；本次确认的固定映射是 GET `/api/user/test` 转发到 `http://localhost:8080/test`，返回下游状态码和响应体。
`test` 和 `zuul` 模块的启动类分别命名为 `TestApplication` 和 `ZuulApplication`。
