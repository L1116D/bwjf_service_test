package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceStatusRequest;
import com.baiwang.bop.respose.entity.InvoiceStatusResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 发票全状态查询
 * baiwang.invoice.status（发票全状态查询）
 * 根据流水单号查询流水单号所关联的所有发票信息，包括开具状态：待开中、开具中、已开具。
 */
public class invoice_status {
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
        try
        {
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            InvoiceStatusRequest request = new InvoiceStatusRequest();
            request.setSerialNo("test0kp005");//开票流水号
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号

            InvoiceStatusResponse response = client.execute(request, token, InvoiceStatusResponse.class);
            //log.info(response.toString());
            System.out.println("响应信息: "+response.toString());
        }
        catch (BopException e)
        {
            e.printStackTrace();
            //invoiceStatus : 11:待开,12:开具中,04:待开作废,00开具成功,03已开作废
            //invoiceOpenType : 0:正数,1:负数

//            log.info("访问失败");
//            log.info(e.getErrCode());
//            log.info(e.getErrMsg());
//            log.info(e.getMessage());
        }



    }
}
