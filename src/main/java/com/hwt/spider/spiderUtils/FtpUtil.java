package com.hwt.spider.spiderUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: hwt
 * @date: 2021/1/18
 * @description: 连接FTP的工具类
 */
@Data
@AllArgsConstructor
public class FtpUtil {

    /**
     * 维护FTPClient实例
     */
    private final static LinkedBlockingQueue<FTPClient> FTP_CLIENT_QUEUE = new LinkedBlockingQueue<>();

    /**
     * 创建目录
     *
     * @param ftpConfig  配置
     * @param remotePath 需要创建目录的目录
     * @param makePath   需要创建的目录
     * @return 是否创建成功
     */
    public static boolean makeDirectory(FtpConfig ftpConfig, String remotePath, String makePath) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.makeDirectory(makePath);
            // 退出FTP
            ftpClient.logout();
            //归还对象
            offer(ftpClient);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移动文件
     *
     * @param ftpConfig 配置
     * @param fromPath  待移动目录
     * @param fromName  待移动文件名
     * @param toPath    移动后目录
     * @param toName    移动后文件名
     * @return 是否移动成功
     */
    public static boolean moveFile(FtpConfig ftpConfig, String fromPath, String fromName, String toPath, String toName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(fromPath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.rename(fromName, toPath + File.separator + toName);
            // 退出FTP
            ftpClient.logout();
            //归还对象
            offer(ftpClient);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除文件
     *
     * @param ftpConfig  配置
     * @param remotePath 远程目录
     * @param fileName   文件名
     * @return 是否删除成功
     */
    public static boolean deleteFile(FtpConfig ftpConfig, String remotePath, String fileName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.deleteFile(fileName);
            // 退出FTP
            ftpClient.logout();
            //归还对象
            offer(ftpClient);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载文件
     *
     * @param ftpConfig  配置
     * @param remotePath 远程目录
     * @param fileName   文件名
     * @param localPath  本地目录
     * @param localName  本地文件名
     * @return 是否下载成功
     */
    public static boolean download(FtpConfig ftpConfig, String remotePath, String fileName, String localPath, String localName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File file = new File(localPath, localName);
            if (!file.getParentFile().exists()) {
                boolean mkdirsResult = file.getParentFile().mkdirs();
                if (!mkdirsResult) {
                    throw new RuntimeException("创建目录失败");
                }
            }
            if (!file.exists()) {
                boolean createFileResult = file.createNewFile();
                if (!createFileResult) {
                    throw new RuntimeException("创建文件失败");
                }
            }
            OutputStream outputStream = new FileOutputStream(file);
            boolean result = ftpClient.retrieveFile(fileName, outputStream);
            outputStream.flush();
            outputStream.close();
            // 退出FTP
            ftpClient.logout();
            //归还对象
            offer(ftpClient);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传文件
     *
     * @param ftpConfig   配置
     * @param remotePath  远程目录
     * @param inputStream 待上传文件输入流
     * @param fileName    文件名
     * @return 是否上传成功
     */
    public static boolean upload(FtpConfig ftpConfig, String remotePath, InputStream inputStream, String fileName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置流上传方式
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            // 设置二进制上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //中文存在问题
            // 上传 fileName为上传后的文件名
            boolean result = ftpClient.storeFile(fileName, inputStream);
            // 关闭本地文件流
            inputStream.close();
            // 退出FTP
            ftpClient.logout();
            //归还对象
            offer(ftpClient);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录ftp
     *
     * @param ftpConfig 配置
     * @return 是否登录成功
     * @throws IOException
     */
    private static FTPClient connectClient(FtpConfig ftpConfig) throws IOException {
        FTPClient ftpClient = getClient();
        // 连接FTP服务器
        ftpClient.connect(ftpConfig.ip, ftpConfig.port);
        // 登录FTP
        ftpClient.login(ftpConfig.userName, ftpConfig.password);
        // 正常返回230登陆成功
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new RuntimeException("连接ftp失败");
        }
        ftpClient.setControlEncoding("GBK");
        System.out.println(reply + "连接成功！");
        return ftpClient;
    }

    /**
     * 获取ftpClient对象
     *
     * @return 获取client对象
     */
    private static FTPClient getClient() {
        FTPClient ftpClient = FTP_CLIENT_QUEUE.poll();
        if (ftpClient != null) {
            return ftpClient;
        }
        return new FTPClient();
    }

    private static void offer(FTPClient ftpClient) {
        FTP_CLIENT_QUEUE.offer(ftpClient);
    }

    /**
     * 连接ftp配置
     */
    public static class FtpConfig {
        private String ip;
        private int port;
        private String userName;
        private String password;

        public FtpConfig setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public FtpConfig setPort(int port) {
            this.port = port;
            return this;
        }

        public FtpConfig setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public FtpConfig setPassword(String password) {
            this.password = password;
            return this;
        }
    }

    public static void main(String[] args) throws IOException {
        //连接配置
        FtpUtil.FtpConfig ftpConfig = new FtpUtil.FtpConfig().setUserName("root")
                .setPassword("h1415128257.").setIp("47.100.164.178").setPort(21);
        FtpUtil.connectClient(ftpConfig);
    }
}
