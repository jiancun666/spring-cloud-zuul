本项目是 Maven 多模块工程，包含 `test` 和 `zuul` 模块，父 POM 继承 Spring Boot Web 依赖。
网关模块 `zuul` 监听 8081；网关返回下游状态码和响应体。
`test` 和 `zuul` 模块的启动类分别命名为 `TestApplication` 和 `ZuulApplication`。
网关当前监听 8081；用户请求的路由规则为 GET `/api/user/**` 去掉前缀后转发到 `http://localhost:8080/**`，并保留查询参数。
