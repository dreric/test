package com.lxcm.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Classname MD5Util
 * @Description TODO MD5加密工具类
 * @Date 2020-03-15 12:34
 * @Created by lx
 */
public class MD5Util {
    //散列次数
    private static int hashIterations =3;
    //内置salt
    private static String public_salt ="F38FB6A41A4C4BFEAE530EDF089EC0B9";

    /**
     * md5内部原始加密
     * @param source 要用公盐加密的字符串
     * @return
     */
    private static String md5_public_salt(String source){
        return new Md5Hash(source,public_salt,hashIterations).toString();
    }

    /**
     *
     * @param source 原始密码
     * @param salt 私有盐
     * @return
     */
    public static String md5_private_salt(String source,String salt){
        return new Md5Hash(md5_public_salt(source),salt,hashIterations).toString();
    }
}
