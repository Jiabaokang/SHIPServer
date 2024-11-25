package com.ljrh.fxjava.fxclient.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ljrh.fxjava.fxclient.utils.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.*;
import java.net.Socket;

@Slf4j
public class LoginController {


    @FXML
    public TextArea resultContent;

    @FXML
    public TextArea inputContent;

    @FXML
    TextField urlField;

    @FXML
    TextField portField;

    @FXML
    public void sendSocketRequest(ActionEvent event) {
        String url = urlField.getText();
        String port = portField.getText();
        String content = inputContent.getText();
        if (url.isEmpty() ) {
            AlertDialog.showAlert("请输入服务器地址");
            return;

        }
        if (port.isEmpty()){
            AlertDialog.showAlert("请输入服务器端口");
            return;
        }
        if (content.isEmpty()){
            AlertDialog.showAlert("请输入内容");
            return;
        }
        createSocketConnect(url,port,content);
    }

    @FXML
    public void sendHttpRequest(ActionEvent event) {
        //sendHttpPostRequest();
        sendHttpGetRequest();
    }

    @FXML
    public void sendXmlRequest(ActionEvent event) {
        //sendHttpPostRequest();
        sendXmlPostRequest();
    }

    private void sendXmlPostRequest() {
        String testData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MailRequestXml>\n" +
                "    <to>jbksy@foxmail.com</to>\n" +
                "    <subject>验证XML数据是否发送成功</subject>\n" +
                "    <content>发送xml格式数据</content>\n" +
                "</MailRequestXml>";
        String testUrl = "http://localhost:8080/send-mail-send-xml";

        String url = urlField.getText()+":"+portField.getText();
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<RequestCall>\n" +
                "    <RequestName>OnlineDoor.Open</RequestName>\n" +
                "    <Params>\n" +
                "        <ExtDoorIDList>\n" +
                "            <EXtDOOrID>D123</ExtDoorID>\n" +
                "            <EXtDOOrID>D456</ExtDoor1D>\n" +
                "        </ExtDoorIDList>\n" +
                "    </Params>\n" +
                "</RequestCall>";

        if (inputContent.getText().isEmpty()) {
            inputContent.setText(xmlData);
        }

        // 1、构建请求体
        String contentType = " application/vnd.saltosystems.ship.v1+xml";
        RequestBody requestBody = RequestBody.create(MediaType.parse(contentType), inputContent.getText());
        // 2、构建请求头
        Request request = new Request.Builder()
             .url(url)
             .post(requestBody)
             .header("Content-Type", contentType)
             .build();

        //创建一个OkHttpClient对象
        OkHttpClient client = new OkHttpClient();

        try{

            log.info("inputContent:{}",inputContent.getText());
            log.info("url:{}",url);
            log.info("request:{}",request.body().contentType(),"\n"+request.body().contentLength());

            // 3、发送请求,获取响应
            Response response = client.newCall(request).execute();
            // 4、处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                log.info("POST请求xml格式发送请求成功:{}",responseBody);

                StringBuilder sb = new StringBuilder();
                sb.append(request.headers().get("Content-Type"))
                        .append("\n")
                        .append(response.headers().get("Content-Length"))
                        .append("\n")
                        .append(response.headers().get("Content-Type"))
                        .append("\n")
                        .append(responseBody);

                resultContent.setText(sb.toString());
            }else {
                System.out.println("Request failed with code: " + response.code());
                resultContent.setText("Request failed with code: " + response.code());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sendHttpGetRequest() {
        String url = "https://demo-api.apipost.cn/api/demo/news_details?id=20&status=1";
        if (inputContent.getText().isEmpty()) {
            inputContent.setText("请求地址为:"+url);
        }

        // 1、构建请求体
        OkHttpClient client = new OkHttpClient();
        //RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml"), xmlData);
        // 2、构建请求头
        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Content-Type", "application/xml")
                .build();
        try {
            // 3、发送请求,获取相应
            Response response = client.newCall(request).execute();
            // 4、处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                log.info("GET方式请求数据成功:{}",responseBody);
                resultContent.setText(responseBody);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendHttpPostRequest() {
        String url = urlField.getText();
        String xmlData = inputContent.getText();
        if (url.isEmpty() ) {
            AlertDialog.showAlert("请输入服务器地址");
            return;

        }
        if (xmlData.isEmpty()){
            AlertDialog.showAlert("请输入内容");
            return;
        }
        // 1、构建请求体
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml"), xmlData);
        // 2、构建请求头
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Content-Type", "application/xml")
                .build();
        try {
            // 3、发送请求,获取相应
            Response response = client.newCall(request).execute();
            // 4、处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                String resStr = formatJson(responseBody);
                StringBuilder sb = new StringBuilder();
                sb.append(request.headers().get("Content-Type"))
                        .append("\n")
                        .append(request.headers().get("Content-Length"))
                        .append("\n")
                        .append(request.headers().get("Transfer-Encoding"))
                        .append("\n")
                        .append(resStr);


                resultContent.setText(sb.toString());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 创建socket连接
     * @param host 服务器的地址
     * @param port 服务器的端口号
     * @param content 要发送的内容
     */
    private  void createSocketConnect(String host, String port,String content) {
        try {
            // 创建一个Socket连接到服务器
            int portInt = Integer.parseInt(port);
            Socket socket = new Socket(host, portInt);

            // 获取输出流，用于向服务器发送数据
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // 向服务器发送数据
            writer.println(content);
            writer.flush();

            // 接收服务器的响应
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //readLine()方法会阻塞直到接收到数据,根据情况处理超时
            String response = reader.readLine();

            // 处理服务器的响应
            System.out.println("服务器响应：" + response);

            String oldStr = resultContent.getText();
            StringBuilder sb = new StringBuilder();
            if (oldStr.isEmpty()){
                sb.append(response);
            }else{
                sb.append("\n" );
                sb.append(response);
            }
            resultContent.setText(sb.toString());

            // 关闭连接
            //socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatJson(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        return gson.toJson(jsonObject);
    }



}
