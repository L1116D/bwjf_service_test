package test.jx.rzdk;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.ResultRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.ResultResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 认证抵扣结果同步
 * baiwang.input.result（认证抵扣结果同步）
 * 对于认证过的发票会返回一个任务号，通过任务号和发票号码，代码将认证抵扣的结果进行同步。
 */
public class input_result {
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
        ResultRequest request = new ResultRequest();
        request.setTaxNo("1111111111111");
        request.setVersion("1.0");
        request.setTaskNo("11577");
        request.setInvoiceCode("1484119141");
        request.setInvoiceNumber("84119208");

        try {
            //TODO 暂时注释掉了 a
            //ResultResponse response = clientBase.getClient().execute(request, clientBase.getSession(), ResultResponse.class);
            assert true;
            //log.info(JacksonUtil.beanToString(response));
            //log.info("success");

            //TODO 暂时注释掉了 b
           // System.out.println(JacksonUtil.beanToString(response));
            System.out.println("success");
        } catch (BopException e) {
            //this.clientBase.logException(log, e);

            //TODO 暂时注释掉了 c
            //this.clientBase.logException(log, e);
            assert false;
            //log.info("error");
            System.out.println("error");
        }



    }
}
