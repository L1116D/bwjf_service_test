package test.jx.qs;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.SyncRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.SyncResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

/**
 * 取数
 * baiwang.input.input_sync（取数）
 * 获取当前税号下的可认证的（在认证期内360天）增值税专用发票和机动车销售统一发票，已取的发票国税状态发生变化可再次获取；
 */
public class input_sync {
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

        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        SyncRequest request = new SyncRequest();
        //request.setTaxNo("1111111111111");//税号
        request.setTaxNo("1111111111111");//税号
        request.setVersion("1.0");//版本号 1.0

        try
        {
            SyncResponse response = client.execute(request, token, SyncResponse.class);
            assert true;
//            log.info(JacksonUtil.beanToString(response));
//            log.info("success");
            System.out.println(JacksonUtil.beanToString(response));
            System.out.println("success");

        }
        catch (BopException e)
        {
            assert false;
            //log.info("error");
            System.out.println("error");
        }
    }
}
