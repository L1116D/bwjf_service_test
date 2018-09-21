package test.yhtx.yhgl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.UpdateUserRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.UpdateUserResponse;
import test.util.StaticTemp;

/**
 * 用户更新
 * baiwang.user.tenant.updateUser（用户更新）
 * 根据请求更新用户信息
 */
public class user_tenant_updateUser {
    public static void main(String[] args) {
        //登录授权获取Token获取 tocken
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAppkey(StaticTemp.getAppkey());
        loginRequest.setAppSecret(StaticTemp.getAppSecret());
        loginRequest.setUserName(StaticTemp.getUserName());
        loginRequest.setPasswordMd5(StaticTemp.getPassword());
        loginRequest.setUserSalt(StaticTemp.getUserSalt());
        ILoginClient loginClient = new PostLogin(StaticTemp.getUrl());
        LoginResponse loginResponse = loginClient.login(loginRequest);
        String token=loginResponse.getAccess_token();//获取token

        try {
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            UpdateUserRequest request = new UpdateUserRequest();

            //必输项 2个
            request.setUserName("");//用户名称
            //TODO 这里需要一个唯一标识 “用户主键”
            request.setUserFlag("");//用户唯一标识

//            request.setUserLoginAccount("test_fish");//用户登录账号
//            request.setUserName("测试鱼2");//用户名称
//            request.setPwd("fish");//用户登录密码
//            request.setUserDescription("");//用户登录描述
//            request.setEmail("");//用户邮箱
//            request.setPhone("15111111111");//用户电话
//            request.setUserType("");//用户类型 0-管理员 1-开票员 2-报税员 默认：0 注册为管理员后可登录开放平台；多个角色值使用逗号分离（0，1，2）

             //...
            UpdateUserResponse response = client.execute(request, token, UpdateUserResponse.class);
            //log.info(response.toString());
            System.out.println(response.toString());
        } catch (BopException e) {
            e.printStackTrace();
//            log.info("访问失败");
//            log.info(e.getErrCode());
//            log.info(e.getErrMsg());
//            log.info(e.getMessage());
            System.out.println("访问失败");
            System.out.println(e.getErrCode());
            System.out.println(e.getErrMsg());
            System.out.println(e.getMessage());
        }
    }
}
