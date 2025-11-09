package com.zzyl;

import com.zzyl.common.utils.PDFUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/10 03:23
 */

public class PDFTest {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream fis=new FileInputStream("C:\\Users\\AngelaEzioHe\\Desktop\\中州养老资料\\中州养老-全资料\\09. 智能评估-集成AI大模型\\资料\\体检报告样例\\体检报告-刘爱国-男-69岁.pdf");
        String content = PDFUtil.pdfToString(fis);
        System.out.println(content);
    }
}
