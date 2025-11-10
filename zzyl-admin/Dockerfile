# 基础镜像
FROM openjdk:11.0-jre-buster
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 使用参数化方式设置密钥，而不是硬编码
ENV OSS_ACCESS_KEY_ID = ""
ENV OSS_ACCESS_KEY_SECRET =""
# 拷贝jar包
COPY zzyl-admin.jar /app.jar
# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]