package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.bizinfo.CompanySearchRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.bizinfo.CompanySearchResponse;
import test.util.StaticTemp;

/**
 * 云抬头获取
 * baiwang.bizinfo.companySearch(云抬头获取)
 *
 * 支持按照公司名称(或公司名称首字母)模糊查询，或按照公司税号、公司名称或公司名称+税号精确查询，
 * 精确查找时 accuracy 为 true，并且此处请加上 sort 字段，按照侧重于time或者frequency排序，
 * 0表示降序，1表示升序。默认为{“frequency”: 0} 。返回结果为json. 若 status_code 为 200,
 * 则该 json 会包含 result, 其值为一个list, 包含不超过8个结果(详见示例, 示例只展示了一个推荐结果),
 * 并按相似度 score 从大到小排序, 用户从列表中选取最优结果。
 */
public class bizinfo_companySearch {
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
            CompanySearchRequest request = new CompanySearchRequest();
            request.setCompanyName("百望股份有限公司");//沙箱环境只能获取“百望股份”相关信息
            //百望云平台6
            request.setAccuracy("TRUE");//是否精确查找；true表示精确查找;false表示模糊查找；默认false
            request.setSort("{\"frequency\": 0}");//排序方式；0表示降序，1表示升序；默认0
            //request.setTaxId("110108339805094");//企业税号
            request.setTaxId("91500000747150426A");

            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            CompanySearchResponse response = client.execute(request, token, CompanySearchResponse.class);
        }
        catch ( BopException e)
        {
            //log.info("error");
            System.out.println("error");
        }


    }
}
