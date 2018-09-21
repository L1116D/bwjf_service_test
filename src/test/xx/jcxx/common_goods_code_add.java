package test.xx.jcxx;

import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceGoodsCodeAddRequest;
import com.baiwang.bop.respose.entity.InvoiceGoodsCodeAddResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 自定义商品添加
 * baiwang.common.goods.code.add（自定义商品添加）
 *
 * 根据请求添加用户自定义商品编码。
 */
public class common_goods_code_add {
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

        InvoiceGoodsCodeAddRequest request = new InvoiceGoodsCodeAddRequest();

        request.setSellerTaxNo("91500000747150426A");//企业税号
        request.setParentGoodsCode("202000000000000000015");//上级商品编码；只能向下添加10级
        request.setProvince("北京市");//省份
        //TODO 如果添加名字重复，则提示已存在
        request.setGoodsName("product_15");//商品名称
        request.setGoodsTaxRate("0.03");//商品税率
        request.setPriceTaxMark("Y");//含税标志；N：不含税 Y：含税
        request.setPreferentialMark("N");//优惠政策类型；详见商品编码表，preferentialMark=Y必填，preferentialMark=N不必填

        //同步请求
        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        client.execute(request, token, InvoiceGoodsCodeAddResponse.class);


    }
}
