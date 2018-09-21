package test.jx.qs;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.SyncResultRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.SyncResultResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

/**
 * 取数任务接收成功反馈
 * baiwang.input.syncResult（取数任务接收成功反馈）
 *
 * 在请求取数接口后，会生成一个任务编号，一个任务编号标识一批发票，
 * 可以通过此接口将任务编号进行反馈，反馈后表明已收到此批发票，下次服务端将不再继续发送这批发票数据。
 */
public class input_syncResult {
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
        SyncResultRequest request = new SyncResultRequest();
        request.setTaxNo("1111111111111");//税号
        request.setVersion("1.0");//版本号 1.0
        request.setTaskNo("61181819705509927");//任务号

        try
        {
            SyncResultResponse response = client.execute(request, token, SyncResultResponse.class);
            assert true;
            //log.info(JacksonUtil.beanToString(response));
            //log.info("success");
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
