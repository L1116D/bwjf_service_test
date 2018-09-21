package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoicePrintRequest;
import com.baiwang.bop.respose.entity.InvoicePrintResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 *  发票打印
 *  baiwang.invoice.print（发票打印）
 */
//TODO
// 100006:远程服务错误:9999:调用[发票打印接口]异常打印方式[dyfs]不能为空
// 响应信息：{"method":"baiwang.invoice.print","requestId":"e51a94a2-c79c-4f8e-87b7-f0536aba9ddf",
// "errorResponse":{"code":100006,"message":"远程服务错误","subCode":9999,"subMessage":"调用[发票打印接口]异常打印方式[dyfs]不能为空"}}
public class invoice_print {
    public static void main (String[] args) {
        try
        {
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            //获取 tocken
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setAppkey(StaticTemp.getAppkey());
            loginRequest.setAppSecret(StaticTemp.getAppSecret());
            loginRequest.setUserName(StaticTemp.getUserName());
            loginRequest.setPasswordMd5(StaticTemp.getPassword());
            loginRequest.setUserSalt(StaticTemp.getUserSalt());
            ILoginClient loginClient = new PostLogin(StaticTemp.getUrl());
            LoginResponse loginResponse = loginClient.login(loginRequest);
            String token=loginResponse.getAccess_token();//获取token
            InvoicePrintRequest request = new InvoicePrintRequest();
            //打印数据
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setSerialNo("test0kp001");//开票流水号，唯一标志开票请求。支持数字字母下划线组合。
            //发票种类编码 004:增值税专用发票，007:增值税普通发票，025：增值税卷式发票, 026：增值税电子发票 （只支持税控服务器，不支持税控盘
            request.setInvoiceTypeCode("026");
            request.setInvoiceTerminalCode("kpyuan002");//开票点编码
            request.setDeviceType("0");//设备类型 0税控服务器，1税控盘
            request.setTaxDiskNo("");//税控盘编号，设备类型为1时必填
            //request.setTaxDiskNo("");
            //request.setTaxDiskKey("");
            //request.setTaxDiskPassword("");
            //TODO 这个提示有问题，invoiceCode 应该是 发票代码（不能为空）
            request.setInvoiceNo("82597817");//发票号码
            request.setInvoiceCode("150003521055");//发票代码
            //打印方式 0：每次点击都弹出打印设置框（目前不支持） 1：只在第一次点击时弹出打印设置框。默认为1
            request.setInvoicePrintType("1");
            //打印类型 弹窗模式 0：发票打印，1：清单打印 不弹窗模式 8：清单打印, 9：发票打印 （打印类型 8和9 只适用于盘用户，核心板用户不适用）
            //TODO 配置信息有误
            request.setInvoicePrintMode("0");

            InvoicePrintResponse response = client.execute(request, token, InvoicePrintResponse.class);
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
