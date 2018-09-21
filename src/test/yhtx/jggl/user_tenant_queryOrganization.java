package test.yhtx.jggl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.QueryOrganizationRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.QueryOrganizationResponse;
import test.util.StaticTemp;

/**
 * 机构查询
 * baiwang.user.tenant.queryOrganization（租户内部组织机构查询）
 * 查询组织信息
 */
public class user_tenant_queryOrganization {
    public static void main(String args) {

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
            QueryOrganizationRequest request = new QueryOrganizationRequest();
            request.setTenantFlag("D1AC2A7883CF74A905A46463CC97F2BC");//租户唯一标识：用于表示标识哪个租户
            request.setOrganizationFlag("9DC7CB609058DAAF350CD065D9B93066");//组织标识：表示维护当前机构的下级机构

            QueryOrganizationResponse response = client.execute(request, token, QueryOrganizationResponse.class);
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
