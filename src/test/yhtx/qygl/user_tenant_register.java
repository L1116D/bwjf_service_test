package test.yhtx.qygl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.RegisterRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.RegisterResponse;
import test.util.StaticTemp;

/**
 * 税控盘企业注册
 * baiwang.user.tenant.register（税控盘企业注册）
 *
 * 合并注册，企业注册+用户注册+盘终端注册。
 *
 * 注意：此接口只适用于代建别的企业，需要拥有一个APPKEY。
 */
public class user_tenant_register {
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
            RegisterRequest request = new RegisterRequest();
            request.setDeviceType("0");//1：税控盘
            request.setTenantName("test_税控盘企业_001");//企业名称
            request.setOrganizationName("test_税控盘企业_001");//组织名称
            request.setOrganizationType("2");//1纳税主体 2非纳税主体；用于区分租户根节点为实体企业或非实体企业
            request.setAuthorizationDocFlow("1");//授权文件流
            request.setQualificationDocFlow("1");//资质文件流
            request.setProductMark("1");//产品标志 云销项 0 云进项 1 移动开票 2 易票宝 3 发票帮 4 电票通 5 扫码抵扣 6 合规查验 7 开发平台 8 云打印 9 无 -1
            request.setAppKey(StaticTemp.getAppkey());

            RegisterResponse response = client.execute(request, token, RegisterResponse.class);
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
