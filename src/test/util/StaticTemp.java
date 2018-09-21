package test.util;

import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.respose.entity.LoginResponse;

public class StaticTemp {

    //公共参数
    public static String url = "http://60.205.83.27/router/rest";             //接口地址
    public static String appkey="10000005";                                   //AppKey
    public static String appSecret="b65025d0-19d2-4841-88f4-ff4439b8da58";    //AppSecrect
    public static String userName="admin_1800000021168";                      //用户名
    public static String password="a123456";                                   //密码
    public static String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值

    public static String getUrl() {
        return url;
    }
    public static void setUrl(String url) {
        StaticTemp.url = url;
    }
    public static String getAppkey() {
        return appkey;
    }
    public static void setAppkey(String appkey) {
        StaticTemp.appkey = appkey;
    }
    public static String getAppSecret() {
        return appSecret;
    }
    public static void setAppSecret(String appSecret) {
        StaticTemp.appSecret = appSecret;
    }
    public static String getUserName() {
        return userName;
    }
    public static void setUserName(String userName) {
        StaticTemp.userName = userName;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        StaticTemp.password = password;
    }
    public static String getUserSalt() {
        return userSalt;
    }
    public static void setUserSalt(String userSalt) { StaticTemp.userSalt = userSalt;}


}
