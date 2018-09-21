package test.yhtx.jggl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.tenant.AddOrganizationRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.tenant.AddOrganizationResponse;
import test.util.StaticTemp;

/**
 * 创建机构
 * baiwang.user.tenant.addOrganization（建立租户内部关系）
 * 添加租户内部组织机构上下级关系。
 */
public class user_tenant_addOrganization {


    //            request.setUserLoginAccount("test_fish01");//用户登录账号
    //            request.setUserName("测试鱼01");//用户名称
    //            request.setPwd("fish01");//用户登录密码
/*    public static String url = "http://60.205.83.27/router/rest";             //接口地址
    public static String appkey="10000005";                                   //AppKey
    public static String appSecret="fb715894-1c52-4319-9b1c-db1e7af7b0eb";    //AppSecrect
    public static String userName="admin_1800000021168";                      //用户名
    public static String password="fish01";                                   //密码
    public static String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值*/


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
            AddOrganizationRequest request = new AddOrganizationRequest();
            //request.setTenantName("qqqqqqq");
            //request.set
            //request.setTenantFlag("554528E1D594075738AB46225416A178");//租户唯一标识；用于表示标识哪个租户
            request.setTenantFlag("F1DD124FD80B76C22A4C53E698A33C4B");//租户唯一标识；用于表示标识哪个租户

            request.setSuporganizationFlag("747C1893B124E501E927635B483472B0");//上级组织标识：表示维护当前机构的下级机构
            request.setOrganizationName("测试hehe");//组织名称，公司名称
            request.setOrganizationType("1");//组织类型：1、纳税主体2、非纳税主体；用于区分租户根节点为实体企业或非实体企业
            request.setOrganizationTax("11111111000000000");//组织税号,即公司税号,纳税主体机构必填

            AddOrganizationResponse response = client.execute(request, token, AddOrganizationResponse.class);
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
