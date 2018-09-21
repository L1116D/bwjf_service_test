package test.yhtx.qygl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.AdminTaxNoInfoRequest;
import com.baiwang.bop.respose.entity.AdminTaxNoInfoResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

import static test.util.StaticTemp.appSecret;

/**
 * 纳税人基础信息查询
 * baiwang.invoice.admin.getTaxNoInfo（纳税人基础信息查询）
 * 纳税人基础信息接口，通过纳税人识别号、纳税人名称返回该纳税人的发行信息。
 */
public class invoice_admin_getTaxNoInfo {
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

            AdminTaxNoInfoRequest request = new AdminTaxNoInfoRequest();
//            request.setSellerName("质保012");//纳税人名称
//            request.setSellerTaxNo("500102020170810");//纳税人识别号

            request.setSellerName("百望股份有限公司");//纳税人名称
            request.setSellerTaxNo("91500000747150426A");//纳税人识别号

            AdminTaxNoInfoResponse response = client.execute(request, token, AdminTaxNoInfoResponse.class);
            //log.info(response.toString());
            System.out.println(response.toString());
        }
        catch (BopException e)
        {
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
