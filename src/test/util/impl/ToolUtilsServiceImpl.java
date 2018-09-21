package test.util.impl;

import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.IToolUtilsService;

public class ToolUtilsServiceImpl implements IToolUtilsService {
    @Override
    public String getToken() {
        //公共参数
        String url = "http://60.205.83.27/router/rest";             //接口地址
        String appkey="10000005";                                   //AppKey
        String appSecret="b65025d0-19d2-4841-88f4-ff4439b8da58";    //AppSecrect
        String userName="admin_1800000021168";                      //用户名
        String password="a123456";                                   //密码
        String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值b
        String token = "";
        //获取 tocken
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAppkey(appkey);
        loginRequest.setAppSecret(appSecret);
        loginRequest.setUserName(userName);
        loginRequest.setPasswordMd5(password);
        loginRequest.setUserSalt(userSalt);
        ILoginClient loginClient = new PostLogin(url);
        LoginResponse loginResponse = loginClient.login(loginRequest);
        token=loginResponse.getAccess_token();//获取token
        return token;
    }
}
