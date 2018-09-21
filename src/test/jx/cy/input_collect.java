package test.jx.cy;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.CollectRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.CollectResponse;
import test.util.StaticTemp;

/**
 * 全票面信息
 * baiwang.input.collect（全票面信息）
 *
 * 根据发票代码，号码，开票日期，校验码后六位，金额等信息获取发票的全票面信息。
 */
//TODO Error "code":100006,"message":"远程服务错误","subCode":5,"subMessage":"开票日期[BillingDate]不正确"}

public class input_collect {
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

        CollectRequest request = new CollectRequest();
        request.setVersion("1.0");//版本号 1.0
        request.setCheckCode("978101");//校验码后6位（普票、电子普票、卷式普票必填）
        request.setBillingDate("201708");//开票日期：YYYY-MM-DD
        request.setInvoiceCode("1100172320");//发票代码
        request.setInvoiceNumber("19477320");//发票号码
        request.setTaxCheck("0");//0-调用税局不校验账号,成本高很多倍；1（非0）-调用税局校验账号
        request.setTotalAmount("54.37");//合计金额（不含税），必须精确到两位小数（专票、货运专票、机动车专票必填）

        try
        {
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            CollectResponse response = client.execute(request, token, CollectResponse.class);
        }
        catch (BopException e)
        {
            //log.info("error");
            System.out.println("error");
        }

    }
}
