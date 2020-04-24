package com.zhancheng.applet.common;

import com.alibaba.fastjson.JSONObject;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author tangchao
 */
public class RequestUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RequestUtil.class);
    private static final int code = 200;

    /**
     * 请求服务器
     *
     * @param location 路径地址
     * @return
     * @throws Exception
     */
    public static JSONObject request(String location) throws IOException {
        StringBuilder result = new StringBuilder();

        URL url;

        int responsecode;

        HttpURLConnection urlConnection;

        BufferedReader reader;

        String line;

        //生成一个URL对象


        url = new URL(location);

        //打开URL

        urlConnection = (HttpURLConnection) url.openConnection();

        //获取服务器响应代码

        responsecode = urlConnection.getResponseCode();

        if (responsecode == code) {

            //得到输入流，即获得了网页的内容

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            try {

                while ((line = reader.readLine()) != null) {
                    result.append(result + line + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }


        } else {
            LOG.error("获取不到网页的源码，服务器响应代码为：" + responsecode);
            throw new MyException(CodeMsg.WECHAT_ERROR);

        }
        return JSONObject.parseObject(result.toString());

    }


    private static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuilder buffer = new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}" + e);
        }
        return null;
    }


    public static void writeResp(HttpServletResponse response, String result) {
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
