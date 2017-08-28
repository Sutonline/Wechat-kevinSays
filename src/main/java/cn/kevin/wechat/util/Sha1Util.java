package cn.kevin.wechat.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha1 加密解密工具类
 * Created by yongkang.zhang on 2017/8/28.
 */
@Slf4j
public class Sha1Util {

    /**
     * 使用SHA-1算法加密字符串
     * @param plainStr 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String plainStr) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(plainStr.getBytes());
            byte[] messageDigest = digest.digest();
            //Create Hex String
            // 字节数组转化为16进制
            for (byte aMessageDigest : messageDigest) {
                String s = Integer.toHexString(aMessageDigest & 0xFF);
                if (s.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("未找到SHA-1算法");
            throw new UnsupportedOperationException("未找到SHA-1算法!");
        }
        return hexString.toString();
    }
}
