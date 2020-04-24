# 湛程小程序接口代码

## 组织架构
zc-program

├── eureka-server -- 服务注册中心

├── config-core -- 共用的核心配置

├── applet-service -- 小程序接口  

├── backstage-service -- 后端管理接口 

├── feign-service -- feign负载均衡代替ribbon 

├── gateway-service -- gateway网关代替zuul

├── ribbon-service -- 负载均衡(被代替)

├── zuul-service -- 动态路由 (被代替)

└── config-server --分布式配置中心


## 使用技术
1. Spring Cloud Finchley
2. redis组件 
3. mybatis和mybatis插件mybatis-plus
4. 阿里数据库连接池
5. 阿里数据库连接池fastjson
6. lombok 简化对象封装工具
7. swagger2 文档生成工具
8. hibernate-validator 用于参数校验

# 如何运行
1. 具备运行环境：JDK1.8,MySql5,Maven3.0,redis5.0
2. 根据修改参数创建对应MySql数据库（数据库编码：UTF-8）。
3. 修改项目配置文件,配置端口号等
3. 首先运行服务注册中心,其次运行分布式配置中心(可不配置),运行接口项目,启动负载均衡(可不配置),启动网关(可不配置)

# 权限管理和接口版本控制

*配置文件地址:config-core com\zhancheng\core\config\security

*使用技术:自定义注解@Verify,AOP,redis,slf4j

*实现功能:可以实现接口的版本控制,用户角色分为三种:后台管理员用户,普通用户,游客用户(如果需要粒度更小可以更改配置文件),
日志打印功能

*使用方法:在需要验证的接口上添加@Verify注解(所有接口都应当添加此注解,默认版本号是1,游客权限),需要后台管理员登录才能
访问的接口需要添加@Verify(role = UserIdentity.ADMIN),小程序微信用户登录需添加@Verify(role = UserIdentity.ORDINARY)
如果不需要权限校验默认是游客权限,添加@Verify即可
