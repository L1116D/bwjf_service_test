package test.yhtx.qygl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceCompanyVerifyQueryRequest;
import com.baiwang.bop.respose.entity.InvoiceCompanyVerifyQueryResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 企业信息审核查询
 * baiwang.common.company.verify.query（企业信息审核查询）
 * 根据税号查询企业的审核状态
 */
public class common_company_verify_query {
    public static void main(String[] args) {
        try
        {
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

            InvoiceCompanyVerifyQueryRequest request = new InvoiceCompanyVerifyQueryRequest();
            //request.setTaxNo("110101201611040");//纳税人识别号
            request.setTaxNo("91500000747150426A");//纳税人识别号
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            InvoiceCompanyVerifyQueryResponse response = client.execute(request, token, InvoiceCompanyVerifyQueryResponse.class);
        }
        catch (BopException e)
        {
            e.printStackTrace();
        }

    }
}
