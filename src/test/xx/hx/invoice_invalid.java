package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceInvalidRequest;
import com.baiwang.bop.respose.entity.InvoiceInvalidResponse;
import com.baiwang.bop.respose.entity.LoginResponse;

public class invoice_invalid {
    /**
     * 发票作废
     * baiwang.invoice.invalid（发票作废）
     */
    //公共参数
    public static String url = "http://60.205.83.27/router/rest";             //接口地址
    public static String appkey="10000005";                                   //AppKey
    public static String appSecret="b65025d0-19d2-4841-88f4-ff4439b8da58";    //AppSecrect
    public static String userName="admin_1800000021168";                      //用户名
    public static String password="a123456";                                   //密码
    public static String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值

    public static void main(String[] args) {
        IBopClient client = new BopRestClient(url, appkey, appSecret);
        //获取 tocken
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAppkey(appkey);
        loginRequest.setAppSecret(appSecret);
        loginRequest.setUserName(userName);
        loginRequest.setPasswordMd5(password);
        loginRequest.setUserSalt(userSalt);
        ILoginClient loginClient = new PostLogin(url);
        LoginResponse loginResponse = loginClient.login(loginRequest);
        String token=loginResponse.getAccess_token();//获取token
        try
        {
            InvoiceInvalidRequest request = new InvoiceInvalidRequest();
            request.setInvoiceCode("150003521055");//发票代码
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setInvoiceTerminalCode("kpyuan002");//开票点编码
            request.setDeviceType("0");//设备类型 0税控服务器，1税控盘
            //发票种类编码，004:增值税专用发票，007:增值税普通发票，026：增值税电子发票，025：增值税卷式发票
            request.setInvoiceTypeCode("026");
            request.setInvoiceNo("82597817");//发票号码
            request.setInvoiceCode("150003521055"); // 2.发票代码
            request.setInvoiceInvalidType("1");//作废类型 0：空白票， 1：已开票
            request.setInvoiceInvalidOperator("测试作废人员张三");//作废人

            //...
            InvoiceInvalidResponse response = client.execute(request, token, InvoiceInvalidResponse.class);
            //log.info("访问成功");
            //log.info(response.getInvoiceInvalidDate());
            System.out.println("访问成功");
            System.out.println(response.getInvoiceInvalidDate());
        }
        catch (BopException e)
        {
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
