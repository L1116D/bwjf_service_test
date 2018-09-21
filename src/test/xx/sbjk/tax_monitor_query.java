package test.xx.sbjk;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.MonitorRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.MonitorResponse;
import test.util.StaticTemp;

/**
 * 监控信息查询
 * baiwang.tax.monitor.query（监控信息查询 ）
 */
public class tax_monitor_query {
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
            MonitorRequest request = new MonitorRequest();
            request.setDeviceType("0");//设备类型 0税控服务器，1税控盘
            request.setInvoiceTypeCode("026");//发票种类编码:004:增值税专用发票，007:增值税普通发票，026：增值税电子发票
            request.setMachineNo("499111005221");//机器编号，当设备类型为0时，必填
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            MonitorResponse response = client.execute(request, token, MonitorResponse.class);
            //log.info(response.getCumulativeInvoiceMoneyLimit() + "");
            System.out.println(response.getCumulativeInvoiceMoneyLimit() + "");
        }
        catch (BopException e)
        {
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
