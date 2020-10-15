package com.hwt.spider.spiderUtils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @Author hwt
 * @Date:13:54 2020/10/12
 * @Description
 */
public class Code {
    private static final char param [] =  "ABCDEFGHIJKLOMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static final char numParam [] =  "1234567890".toCharArray();

    public static String getVerficationCode(){
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        for(int i = 0 ; i < 6 ; i++){
            code.append(param[rand.nextInt(35 - 0 + 1) + 0]);
        }
        return code.toString();
    }

    public static String getAccoutNum(){
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        code.append(numParam[rand.nextInt(9 - 0 + 1) + 0]);
        for(int i = 1 ; i < 10 ; i++){
            code.append(numParam[rand.nextInt(10 - 0 + 1) + 0]);
        }
        return code.toString();
    }
    //md5加密
    public static String md5(byte[] value, boolean bLower) {
        try {
            byte[] encs= md5(value,null,false,0,value.length);
            if(bLower){
                return Hex.encodeHexString(encs).toLowerCase();
            }
            else{
                return Hex.encodeHexString(encs).toUpperCase();
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public static String md5(byte[] value1, byte[] value2, byte[] value3, boolean bLower) {
        try {
            MessageDigest md5 = DigestUtils.getMd5Digest();

            if ((value1 != null) && (value1.length != 0)) {
                md5.update(value1);
            }
            if ((value2 != null) && (value2.length != 0)) {
                md5.update(value2);
            }
            if ((value3 != null) && (value3.length != 0)) {
                md5.update(value3);
            }
            byte[] md5Bytes = md5.digest();
            if(bLower){
                return Hex.encodeHexString(md5Bytes).toLowerCase();
            }else{
                return Hex.encodeHexString(md5Bytes).toUpperCase();
            }
        } catch (Exception ex) {
            return "";
        }
    }


    public static String md5(String data, boolean bLower) {
        try {
            return md5(data.getBytes(),bLower) ;
        } catch (Exception ex) {
            return "";
        }
    }
    public static byte[] md5(byte[] data )   {

        return md5(data,0,data.length);
    }
    public static byte[] md5(byte[] data,int offset,int length)   {
        try {
            return  md5(data,null,false,offset,length);
        }catch (Exception ex){
            return null;
        }
    }
    public static byte[] md5(byte[] data,int offset,int length,byte []salt)   {
        try {
            return  md5(data,salt,false,offset,length);
        }catch (Exception ex){
            return null;
        }
    }
    public static byte[] md5(byte[] data,int offset,int length,byte []salt,byte []data2)   {
        try {
            MessageDigest md5 = DigestUtils.getMd5Digest();
            md5.update(data, offset, length);
            if(data2!=null && data2.length>0){
                md5.update(data2, 0, data2.length);
            }
            if ((salt != null) && (salt.length != 0)) {
                md5.update(salt);
            }
            byte[] md5Bytes = md5.digest();
            return md5Bytes;
        }catch (Exception ex){
            return null;
        }
    }
    public static byte[] md5(byte[] data, byte[] salt, boolean isTwice, int offset, int length)
    {
        MessageDigest md5 = DigestUtils.getMd5Digest();
        md5.update(data, offset, length);
        if ((salt != null) && (salt.length != 0)) {
            md5.update(salt);
        }
        byte[] md5Bytes = md5.digest();
        if (isTwice) {
            md5.update(md5Bytes, 8, 8);
            byte[] md52 = md5.digest();
            System.arraycopy(md52, 8, md5Bytes, 8, 8);
        }
        return md5Bytes;
    }
    public static String md5String(byte[] data, int offset, int length, byte []salt, byte []data2, boolean bLower) {
        try {
            byte []md5Bytes = md5(data,offset,length,salt,data2) ;
            if(bLower){
                return Hex.encodeHexString(md5Bytes).toLowerCase();
            }else {
                return Hex.encodeHexString(md5Bytes).toUpperCase();
            }
        } catch (Exception ex) {
            return "";
        }
    }
}
