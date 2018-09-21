package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.bizinfo.SearchRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.bizinfo.SearchResponse;
import test.util.StaticTemp;
import test.util.ToolUtils;

/**
 * 智能赋码
 * baiwang.bizinfo.search（智能赋码）
 * 帮助用户从云端智能编码库快速获取某个商品对应商品编码的推荐。
 * 智能赋码推荐的商品编码具有匹配度属性，匹配度越高表明该编码准确度越高，一般匹配度超过85%可认为编码正确，低于50%建议用户手动选择编码
 */
public class bizinfo_search {

    ToolUtils toolutils = new ToolUtils();

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

        SearchRequest request = new SearchRequest();
        //request.setCommodityName("小米手机");//沙箱环境只能查询“小米手机”和“iphone”
        request.setCommodityName("iphone");

        try{//同步请求
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(),StaticTemp.getAppSecret());
            //String tempToken = toolutils.getToken();
            SearchResponse response = client.execute(request, token, SearchResponse.class);;
            //log.info("success");
            System.out.println("SUCCESS");
        } catch ( BopException e){
            //log.info("error");
            System.out.println("ERROR");
        }

    }
}
