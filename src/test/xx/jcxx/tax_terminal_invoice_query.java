package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.InvoiceTerminalQueryRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.InvoiceTerminalQueryResponse;
import test.util.StaticTemp;

/**
 * 开票终端信息查询
 * baiwang.user.terminal.invoice.query（开票终端信息查询）
 *
 * 根据纳税人识别号，设备类型，设备编号获取开票终端信息。
 */
public class tax_terminal_invoice_query {
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
            InvoiceTerminalQueryRequest request = new InvoiceTerminalQueryRequest();
//            request.setSellerTaxNo("91500000747150398A");//销方企业税号
//            request.setDeviceType("1");//设备类型 0税控服务器，1税控盘
//            request.setMachineNo("499000150434");//设备编号，设备类型 为0时写机器编号，为1时写税控盘编号

            request.setSellerTaxNo("91500000747150426A");//销方企业税号
            request.setDeviceType("1");//设备类型 0税控服务器，1税控盘
            request.setMachineNo("test001");//设备编号，设备类型 为0时写机器编号，为1时写税控盘编号

            InvoiceTerminalQueryResponse response = client.execute(request, token, InvoiceTerminalQueryResponse.class);
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
