package test.xx.jcxx;

import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceGoodsCodeQueryRequest;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 自定义商品查询
 * baiwang.common.goods.code.query（自定义商品查询）
 *
 * 根据请求查询用户自定义商品编码。
 */
//TODO ERROR 100006:远程服务错误:9999:调用[商品管理查询]接口成功，但未查到数据！
public class common_goods_code_query {
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
        InvoiceGoodsCodeQueryRequest request = new InvoiceGoodsCodeQueryRequest();
        //TODO 商品编码和商品名称2选一必填
       //request.setGoodsCode("1010101020000000000");//商品编码
        request.setGoodsCode("");
        request.setGoodsName("product_15");//商品名称 //
        request.setProvince("重庆市");//省份
        request.setSellerTaxNo("91500000747150426A");//企业税号
        request.setShowChildNodes("Y");//是否显示子节点 N/Y

        //同步请求
        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        client.execute(request, token, InvoiceGoodsCodeQueryRequest.class);

    }
}
