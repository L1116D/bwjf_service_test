package test.xx.jcxx;

import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceTerminalAddRequest;
import com.baiwang.bop.respose.entity.InvoiceTerminalAddResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 开票终端添加
 * baiwang.tax.terminal.add（开票终端添加）
 *
 * 根据请求添加开票终端，支持服务器与税控盘。
 */
public class tax_terminal_add {
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

        InvoiceTerminalAddRequest request = new InvoiceTerminalAddRequest();

        request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
        request.setDeviceType("1");//设备类型 0税控服务器，1税控盘
        //税控服务器
        request.setMachineNo("test0017");//设备编号，当设备类型为0时，必填
        //税控盘
        request.setTaxDiskNo("test0017");//税控盘编号，设备类型为1时必填
        request.setTaxDiskKey("test0017");//税控盘口令，设备类型为1时必填
        request.setTaxDiskPassword("test0017");//税务数字证书密码，设备类型为1时必填
        request.setTaxDiskLicenseCode("test0017");//注册码，设备类型为1时必填

        request.setInvoiceTerminalCode("CSKPD0017");//开票点编码
        request.setInvoiceTerminalName("12345662");//开票点名称
        request.setInvoiceTypeCode("004,007");//发票种类编码 004/005/007/025/026 以英文逗号隔开
        request.setSellerAddress("北京海淀");//销货单位地址
        request.setSellerPhone("15117629502");//销货单位电话
        request.setSellerBank("北京银行");//销货单位开户行
        request.setSellerAccount("622313212333");//销货单位开户行账号

        //同步请求
        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        InvoiceTerminalAddResponse response = client.execute(request, token, InvoiceTerminalAddResponse.class);

    }

}
