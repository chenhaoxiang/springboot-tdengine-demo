## TDengine SpringBoot + Mybatis Demo

### 配置 application.properties
```properties
# datasource config - JDBC-RESTful
spring.datasource.driver-class-name=com.taosdata.jdbc.rs.RestfulDriver
spring.datasource.url=jdbc:TAOS-RS://你的IP地址:6041/demo?timezone=UTC-8&charset=UTF-8&locale=en_US.UTF-8
spring.datasource.username=root
spring.datasource.password=taosdata

spring.datasource.druid.initial-size=5
spring.datasource.druid.min-idle=5
spring.datasource.druid.max-active=5
spring.datasource.druid.max-wait=30000
spring.datasource.druid.validation-query=select server_status();

#mybatis
#mybatis.mapper-locations=classpath:mapper/*.xml

logging.level.com.taosdata.example.springbootdemo.dao=debug

server.port=8081
```

### 主要功能

* 创建数据库和表
```xml
<!-- weatherMapper.xml -->
 <update id="createDB" >
        create database if not exists test;
    </update>

    <update id="createTable" >
        create table if not exists test.weather(ts timestamp, temperature int, humidity float);
    </update>
```

* 插入单条记录
```xml
<!-- weatherMapper.xml -->
    <insert id="insert" parameterType="Weather" >
        insert into test.weather (ts, temperature, humidity) values (now, #{temperature,jdbcType=INTEGER}, #{humidity,jdbcType=FLOAT})
    </insert>
```
* 插入多条记录
```xml
<!-- weatherMapper.xml -->
<insert id="batchInsert" parameterType="java.util.List" >
    insert into test.weather (ts, temperature, humidity) values
    <foreach separator=" " collection="list" item="weather" index="index" >
        (now + #{index}a, #{weather.temperature}, #{weather.humidity})
    </foreach>
</insert>
```
* 分页查询
```xml
<!-- weatherMapper.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="WeatherMapper">

    <resultMap id="BaseResultMap" type="Weather">
        <id column="ts" jdbcType="TIMESTAMP" property="ts" />
        <result column="temperature" jdbcType="INTEGER" property="temperature" />
        <result column="humidity" jdbcType="FLOAT" property="humidity" />
    </resultMap>

    <sql id="Base_Column_List">
        ts, temperature, humidity
    </sql>

    <select id="select" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from test.weather
        order by ts desc
        <if test="limit != null">
            limit #{limit,jdbcType=BIGINT}
        </if>
        <if test="offset != null">
            offset #{offset,jdbcType=BIGINT}
        </if>
    </select>
</mapper>
```

