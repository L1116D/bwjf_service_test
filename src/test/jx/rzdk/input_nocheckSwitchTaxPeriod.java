package test.jx.rzdk;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.SwitchTaxPeriodRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.SwitchTaxPeriodResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

/**
 * 切换税款所属期
 * baiwang.input.nocheckSwitchTaxPeriod（切换税款所属期）
 */
//TODO ERROR "code":100006,"message":"远程服务错误","subCode":403,"subMessage":"服务调用错误，请提供requestId联系管理员定位问题！"
public class input_nocheckSwitchTaxPeriod {
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
        SwitchTaxPeriodRequest request = new SwitchTaxPeriodRequest();
        request.setVersion("1.0");//版本号 1.0
        request.setTaxNo("440300576380923");//税号
        request.setOpType("1");//切换还是回退（1-切换 2-回退）

        try
        {
            SwitchTaxPeriodResponse executeResponse = client.execute(request, token, SwitchTaxPeriodResponse.class);
            assert true;
//            log.info(JacksonUtil.beanToString(response));
//            log.info("success");
            System.out.println(JacksonUtil.beanToString(executeResponse));
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
