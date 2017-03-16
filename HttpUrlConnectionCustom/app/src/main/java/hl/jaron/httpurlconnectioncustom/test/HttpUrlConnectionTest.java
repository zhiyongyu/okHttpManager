package hl.jaron.httpurlconnectioncustom.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 此类用于HttpUrlConnection测试。
 * 通过基础的创建连接和请求数据
 * 能够对HttpUrlConnection有一定的认识和了解。
 * 相信每一种网络请求方式都是大同小异，只要知道了原理，
 * 使用其他的也只是换一种工具罢了。
 * Created by Jaron Yu on 2017/3/16.
 */
public class HttpUrlConnectionTest {

    public HttpUrlConnectionTest() {
        Thread thread = new Thread(getConnection);
        thread.start();
    }

    public void getHttpConnection() {
        HttpURLConnection conn = null; //首先，创建一个连接对象。
        InputStream is = null;//输入流
        String resultData = "";
        try {
            URL url = new URL("http://www.baidu.com/");//url对象，即需要访问的地址。
            conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setRequestMethod("GET"); //使用get请求
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 最后需要关闭流，以及连接。
             */
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 因为访问网络是个耗时操作，为了不影响用户的其他操作。
     * 访问网络需在子线程中进行（安卓4.0以后的版本强制要求请求网络需在子线程中，否则会Crash）
     */
    Runnable getConnection = new Runnable() {
        @Override
        public void run() {
            getHttpConnection();
        }
    };


}
