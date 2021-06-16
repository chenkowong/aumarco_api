# aumarco_api

## 项目框架

```
latticy
├─ assets        
├─ logs
├─ src
│   ├─ main
│   │   ├─ java
│   │   │   └─ io.github.talelin.latticy
│   │   │                           ├─ controller
│   │   │                           │    ├─ cms Lin-cms接口控制类
│   │   │                           │    └─ v1  业务接口，创建接口控制器
│   │   │                           ├─ dto 请求体校验
│   │   │                           ├─ mapper 定义mapper类
│   │   │                           ├─ model 数据容器（模型层）
│   │   │                           ├─ service 定义mapper类
│   │   │                           │    └─ impl 业务层类
│   │   └─ resources
│   │       └─ mapper 定义模型DO与数据表的映射
│   └─ test
```

## 开发步骤

### 1. 数据层

1.1 使用navicat部署数据表

### 2. 模型层

2.1 在src/main/java/io/github/talelin/latticy/model 设置DO

### 3. 业务层

3.1 src/main/java/io/github/talelin/latticy/mapper 创建mapper

3.2 src/main/resources/mapper 定义模型DO与数据表的映射

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 注意这个namespace要填写正确的mapper文件地址 -->
<mapper namespace="io.github.talelin.latticy.mapper.TestBookMapper">
    <resultMap id="BaseResultMap" type="io.github.talelin.latticy.model.TestBookDO">
        <id column="id" jdbcType="INTEGER" property="id" />
        <result column="title" jdbcType="VARCHAR" property="title" />
        <result column="author" jdbcType="VARCHAR" property="author" />
        <result column="summary" jdbcType="VARCHAR" property="summary" />
        <result column="image" jdbcType="VARCHAR" property="image" />
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime" />
        <result column="update_time" jdbcType="TIMESTAMP" property="updateTime" />
        <result column="delete_time" jdbcType="TIMESTAMP" property="deleteTime" />
    </resultMap>
</mapper>

```

### 4. 控制层

4.1 src/main/java/io/github/talelin/latticy/controller/v1 创建controller文件


