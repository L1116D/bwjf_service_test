package test.jx.qs;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.InitRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.InitResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

/**
 * 初始化发票数据
 * baiwang.input.init（初始化发票数据）
 *
 * 把税号下通过取数接口同步过的发票设置成未同步，可再次通过取数接口获取所有发票
 */
public class input_init {
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

        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        InitRequest request = new InitRequest();
        request.setTaxNo("1111111111111");//税号
        request.setVersion("1.0");//版本号 1.0
        try
        {
            InitResponse response = client.execute(request, token, InitResponse.class);
            assert true;
//            log.info(JacksonUtil.beanToString(response));
//            log.info("success");
            System.out.println(JacksonUtil.beanToString(response));
            System.out.println("success");
        }
        catch (BopException e)
        {
            assert false;
            //log.info("error");
            System.out.println("error");
        }
    }
}
