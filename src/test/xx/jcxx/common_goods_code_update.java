package test.xx.jcxx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceGoodsCodeUpdateRequest;
import com.baiwang.bop.respose.entity.InvoiceGoodsCodeUpdateResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 自定义商品编码维护
 * baiwang.common.goods.code.update（自定义商品编码维护）
 * 根据请求维护用户自定义商品编码。
 */
//TODO ERROR 100006:远程服务错误:9999:调用[商品管理编辑]接口失败:请核对纳税人识别号，商品编码和省份是否对应！
public class common_goods_code_update {
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
            InvoiceGoodsCodeUpdateRequest request = new InvoiceGoodsCodeUpdateRequest();
            request.setSellerTaxNo("91500000747150426A");//企业税号
            request.setGoodsCode("2020000000000000000test");//上级商品编码；只能向下添加10级
            request.setGoodsName("product_01");//商品名称
            request.setGoodsTaxRate("0.03");//商品税率
            request.setPriceTaxMark("Y");//含税标志 N：不含税 Y：含税
            request.setPreferentialMark("N");//是否使用优惠政策；N:未使用，Y:使用

            //---- add
            //request.setPreferentialType("N");//优惠政策类型 preferentialMark=Y 必填 preferentialMark =N 不必填
            request.setGoodsDescribe("test商品说明");//商品说明
            request.setGoodsShorthand("test_jm");//商品简码
            request.setGoodsSpecification("test_gg");//规格型号
            request.setGoodsUnit("");//计量单位
            request.setGoodsPrice("10.00");//商品单价
            request.setFreeTaxType("0");//免税类型 0:正常税率；1:出口免税或其他免税优惠政策；2不征增值税；3普通零税率，默认为0

            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            client.execute(request, token, InvoiceGoodsCodeUpdateResponse.class);
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
