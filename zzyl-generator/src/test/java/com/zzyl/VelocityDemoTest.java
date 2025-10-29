package com.zzyl;

import com.zzyl.generator.util.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @Author: EzioHe
 * @Date: 2025/10/20 15:13
 */
public class VelocityDemoTest {
    public static void main(String[] args) throws IOException {
        VelocityInitializer.initVelocity();

        VelocityContext context = new VelocityContext();
        context.put("message", "加油朋友~");

        Template template = Velocity.getTemplate("vms/index.html.vm", "UTF-8");

        //准备一个输出流，将结果文件写出去
        FileWriter fileWriter = new FileWriter("zzyl-generator\\src\\main\\resources\\index.html");

        //合并模板和数据模型
        template.merge(context, fileWriter);
        fileWriter.close();
    }
}
