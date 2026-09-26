## 项目结构

- 项目是 Maven 多模块工程，包含 `test` 和 `zuul` 模块。
- 父 POM 提供 Spring Boot Web 依赖，Java 编译目标为 21。

## 启动类与端口

- `test` 模块启动类为 `org.example.TestApplication`，监听 8080。
- `zuul` 模块启动类为 `org.example.ZuulApplication`，监听 8081。

## 网关路由

- 网关处理 GET、POST 和 DELETE `/api/user/**` 请求。
- 转发目标为 `http://localhost:8080`，去掉 `/api/user` 前缀后保留剩余路径。
- 转发时保留原请求查询参数；POST 还会转发请求体和 `Content-Type`。
- 网关返回下游响应状态码和响应体。

## 下游测试控制器

- `test` 模块提供 POST `/post`，原样返回请求体。
- `test` 模块提供 DELETE `/delete/{id}`，返回 `deleted:{id}`。
