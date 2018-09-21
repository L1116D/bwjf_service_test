package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceTaxDiskGoodsCodeRequest;
import com.baiwang.bop.respose.entity.InvoiceTaxDiskGoodsCodeResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 商品编码同步
 * baiwamg.invoice.code.queryGoods（商品编码同步）
 * 获取税控盘中自定义的商品编码列表。
 *
 * 注：需要开启百望云助手
 */
//TODO 100006:远程服务错误:9999:百望云客户端未开启或配置信息有误!

public class invoice_code_queryGoods {
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

            InvoiceTaxDiskGoodsCodeRequest request = new InvoiceTaxDiskGoodsCodeRequest();
            request.setSellerTaxNo("500102020170810");//纳税人识别号
            request.setTaxDiskNo("499100001231");//税控盘编号

            InvoiceTaxDiskGoodsCodeResponse response = client.execute(request, token, InvoiceTaxDiskGoodsCodeResponse.class);
//            log.info(response.toString());
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
