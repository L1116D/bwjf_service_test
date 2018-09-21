package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.DeleteTerminalRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.DeleteTerminalResponse;
import test.util.StaticTemp;

/**
 * 开票终端删除
 * baiwang.user.terminal.delete（开票终端删除）
 *
 * 盘终端删除
 */
public class tax_terminal_delete {
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
            DeleteTerminalRequest request = new DeleteTerminalRequest();
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            //request.setTerminalSign("0000000003");//终端标识
            request.setTerminalSign("CSKPD0017");//终端标识

            DeleteTerminalResponse response = client.execute(request, token, DeleteTerminalResponse.class);
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
