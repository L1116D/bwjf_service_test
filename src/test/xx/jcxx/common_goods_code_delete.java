package test.xx.jcxx;

import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceGoodsCodeDeleteRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceGoodsCodeQueryRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 自定义商品删除
 * baiwang.common.goods.code.delete（自定义商品删除）
 * 根据请求删除用户自定义商品编码。
 */
public class common_goods_code_delete {
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

        InvoiceGoodsCodeDeleteRequest request = new InvoiceGoodsCodeDeleteRequest();
        request.setSellerTaxNo("91500000747150426A");//企业税号
        request.setDeviceType("1");//设备类型
        request.setProvince("重庆市");
        request.setGoodsCode("202000000000000000016");//商品编码

        //同步请求
        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        client.execute(request, token, InvoiceGoodsCodeQueryRequest.class);

    }
}
