package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceVehicleOpenRequest;
import com.baiwang.bop.respose.entity.InvoiceVehicleOpenResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 机动车销售发票开具
 * baiwang.invoice.vehicle.open（机动车销售发票开具）
 * 根据请求的发票信息XML，开具机动车销售统一发票。
 */

//TODO 远程服务错误
// 100006:远程服务错误:9999:调用[机动车发票开具]接口失败,原因:10194:当前车辆识别代码/车架号码已经被使用!


public class invoice_vehicle_open {
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
            InvoiceVehicleOpenRequest request = new InvoiceVehicleOpenRequest();
            request.setSerialNo("JDCLSH10003");//开票流水号，唯一标志开票请求。支持数字字母下划线组合。
            request.setDeviceType("0");//设备类型 0税控服务器，1税控盘
            request.setInvoiceTypeCode("005");//发票种类编码，005:机动车销售发票
            request.setInvoiceType("0");//开票类型0:正数发票（蓝票） 1：负数发票（红票）
            request.setInvoiceTerminalCode("810zp");//开票点编码
            request.setSellerTaxNo("500102020170810");//销方单位代码
            request.setSellerName("质保012");//销方单位名称
            request.setBuyerTaxNo("500102020171110");//购方单位代码
            request.setBuyerName("百望股份有限公司111xw");//购方单位名称
            request.setCredentialsNo("412312199004220813");//身份证号码/组织机构代码
            request.setProductionEnterpriseName("吉利");//生产企业名称
            request.setVehicleType("测试机动车001");//车辆类型，与商品编码中商品名称对应
            request.setBrandModelNo("CP001");//厂牌型号
            request.setProducer("北京");//产地
            request.setCertificateNo("HGZH001");//合格证号
            request.setImportCertificateNo("JKZS001");//进口证明书号
            request.setCommodityInspectionNo("SJDH001");//商检单号
            request.setEngineNo("FDJ0011111");//发动机号码
            request.setVehicleIdentificationCode("CLL011203");//车架号码/车辆识别代号，数字 字母 星号
            request.setVoucherNo("wspzhm001");//完税凭证号码,自开为空
            request.setTonnage("10");
            request.setLimitedPassengers("4");
            request.setAddress("北京海淀区");
            request.setPhone("15117929802");
            request.setBank("北京银行");
            request.setAccount("62213119900231231231");
            request.setGoodsCode("3010101020102000000");
            request.setGoodsExtendCode("01");
            request.setGoodsTaxRate("0.11");
            request.setInvoiceTotalPrice("100");
            request.setInvoiceTotalPriceTax("111");
            request.setInvoiceTotalTax("11");
            request.setVatSpecialManagement("");
            request.setFreeTaxMark("");//零税率标识：空代表无， 1 出口免税和其他免税优惠政策， 2 不征增值税， 3 普通零税率
            request.setPreferentialMark("0");//是否使用优惠政策 0:未使用，1:使用
            request.setDrawer("wwm");
            request.setOriginalInvoiceCode("");
            request.setOriginalInvoiceNo("");
            request.setSignatureParameter("");
            request.setTaxDiskKey("");
            request.setTaxDiskNo("");
            request.setTaxDiskPassword("");
            request.setRemarks("");

            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            InvoiceVehicleOpenResponse response = client.execute(request, token, InvoiceVehicleOpenResponse.class);
        }
        catch (BopException e)
        {
//            log.info(e.getErrCode());
//            log.info(e.getErrMsg());
//            log.info(e.getSubMessage());
//            log.info(e.getSubCode());
//            log.info(e.getMessage());
            System.out.println("访问失败");
            System.out.println(e.getErrCode());
            System.out.println(e.getErrMsg());
            System.out.println(e.getMessage());
        }
    }
}
