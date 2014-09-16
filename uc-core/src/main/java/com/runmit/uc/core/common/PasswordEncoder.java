package com.runmit.uc.core.common;

import java.security.MessageDigest;

public class PasswordEncoder {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    //暂时系统用一个salt,后续有需要再改
    private final static String salt = "EYeq4PyBvDERf2pd";

    private final static String algorithm = "MD5";


    //生成token
    public static String  gettoken(String userid,String devicesn) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            String timestamp = String.valueOf(System.currentTimeMillis());
            //激活码
            result = byteArrayToHexString(md.digest((userid + devicesn + timestamp).getBytes("utf-8")));
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    //激活邮箱的验证码
    public static String getvalidata(String userid) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            String timestamp = String.valueOf(System.currentTimeMillis());
            //激活码
            result = byteArrayToHexString(md.digest((userid + timestamp).getBytes("utf-8")));
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    public static boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private static String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        String salt = "helloworld";
        PasswordEncoder encoderMd5 = new PasswordEncoder();
        String encode = encoderMd5.encode("test");
        System.out.println(encode);
        boolean passwordValid = encoderMd5.isPasswordValid("083a8db3ff5b9b4ece3ef2bde03226c8", "test");
        System.out.println(passwordValid);

        PasswordEncoder encoderSha = new PasswordEncoder();
        String pass2 = encoderSha.encode("test");
        System.out.println(pass2);
        boolean passwordValid2 = encoderSha.isPasswordValid("1e4346dcb54c1444e91668e04b8ca4f74f42958e", "test");
        System.out.println(passwordValid2);
    }

}