package test.xx.jcxx;

import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceTerminalUpdateRequest;
import com.baiwang.bop.respose.entity.InvoiceTerminalUpdateResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 开票终端维护
 * baiwang.tax.terminal.update（开票终端维护）
 * 根据请求维护开票终端。
 */
public class tax_terminal_update {
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

        /**
         * 1、登录授权获取Token
         * 2、组装请求类
         * 3、发送请求获取返回值
         */
        //String token = ToolUtils.getToken(Contants.url,Contants.appkey,Contants.appSecret,Contants.userName,Contants.password,Contants.userSalt);

        InvoiceTerminalUpdateRequest request = new InvoiceTerminalUpdateRequest();

        request.setSellerTaxNo("91500000747150426A");//销方纳税人税号
        request.setInvoiceTerminalCode("CSKPD0017");//开票点编码
        request.setSellerAddress("北京海淀2");//销货单位地址
        request.setSellerPhone("15117629503");//销货单位电话
        request.setSellerBank("北京银行44");//销货单位开户行
        request.setSellerAccount("62231321233444");//销货单位开户行账号

        //同步请求
        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        InvoiceTerminalUpdateResponse response = client.execute(request, token, InvoiceTerminalUpdateResponse.class);


    }
}
