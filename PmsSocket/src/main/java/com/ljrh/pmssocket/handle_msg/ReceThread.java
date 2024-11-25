package com.ljrh.pmssocket.handle_msg;

import com.google.gson.Gson;
import com.ljrh.pmssocket.dao.IPmsUserDao;
import com.ljrh.pmssocket.entity.ThirdPmsUser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Slf4j
public class ReceThread extends Thread{


    private final Socket clickSocket;
    private final IPmsUserDao pmsUserDao;
    public ReceThread(Socket socket,IPmsUserDao pmsUserDao) {
        this.clickSocket = socket;
        this.pmsUserDao = pmsUserDao;
    }

    @Override
    public void run() {
        //不停接受数据
        while (true){
            try {
                InputStream is = clickSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int byteRead = is.read(buffer);

                //如果客户端断开连接，byteRead == -1
                if (byteRead == -1){
                    log.info("客户端断开连接");
                    clickSocket.close();
                    break;
                }
                //读取数据
                byte[] bytes = is.readNBytes(byteRead);
                String content = new String(bytes);
                log.info("接受到客户端数据：\n {}" , content);
                //解析数据
                ThirdPmsUser pmsUser = parseData(content);
                //存入数据库
                saveDataToDB(pmsUser);
                //发送数据给Salto服务
                sendDataToSalto(bytes);
            } catch (IOException e) {
                log.error("接收数据异常:\n {}",e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 存入数据库
     * @param pmsUser
     */
    private void saveDataToDB(ThirdPmsUser pmsUser) {
        int insert = pmsUserDao.insert(pmsUser);
        String result = insert == 1?"数据插入成功":"数据插入失败";
        try {
            SendThread.sendData(result,clickSocket);
        } catch (IOException e) {
            log.info("回复数据异常{}",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     * @param content
     */
    private ThirdPmsUser parseData(String content) {
        Gson gson = new Gson();
        ThirdPmsUser pmsUser = gson.fromJson(content, ThirdPmsUser.class);
        log.info("解析数据：\n {}",pmsUser);
        return pmsUser;
    }

    /**
     * 发送数据给Salto服务
     * @param bytes
     */
    private void sendDataToSalto(byte[] bytes) {

        // TODO 发送数据给Salto服务
        log.info("发送byte 数据给Salto服务\n {}",bytes);
        log.info("发送Hex 数据给Salto服务1\n {}",bytesToHex(bytes));

    }

    /**
     * byte数组转16进制字符串
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
