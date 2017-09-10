package com.msr.nbmusic.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.math.BigInteger;

/**
 * Created by Ymmmsick on 2017-09-10.
 */

public class NeteaseCloudMusicUtils {
    public static void loginAPI(String phone, String password) throws Exception {
        password = EncryptUtils.md5(password);
        // 私钥，随机16位字符串（自己可改）
        String secKey = "cd859f54539b24b7";
        String text = "{\"phone\": \"" + phone + "\", \"rememberLogin\": \"true\", \"password\": \"" + password
                + "\"}";
        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String nonce = "0CoJUm6Qyw8W8jud";
        String pubKey = "010001";
        // 2次AES加密，得到params
        String params = EncryptUtils.encrypt(EncryptUtils.encrypt(text, nonce), secKey);
        StringBuffer stringBuffer = new StringBuffer(secKey);
        // 逆置私钥
        secKey = stringBuffer.reverse().toString();
        String hex = EncryptUtils.encodeHexString(secKey.getBytes());
        BigInteger bigInteger1 = new BigInteger(hex, 16);
        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
        BigInteger bigInteger3 = new BigInteger(modulus, 16);
        // RSA加密计算
        BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
        String encSecKey = EncryptUtils.encodeHexString(bigInteger4.toByteArray());
        // 字符填充
        encSecKey = EncryptUtils.zfill(encSecKey, 256);
        System.out.println(params);
        System.out.println(encSecKey);
        // 登录请求
        Document document = Jsoup.connect("http://music.163.com/weapi/login/cellphone/").cookie("appver", "1.5.0.75771")
                .header("Referer", "http://music.163.com/").data("params", params).data("encSecKey", encSecKey)
                .ignoreContentType(true).post();
        System.out.println("登录结果：" + document.text());
    }

    public static void main(String args[]) {
        try {
            loginAPI("18782921303", "5201314");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
