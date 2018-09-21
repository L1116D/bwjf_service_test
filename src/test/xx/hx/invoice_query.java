package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceQueryRequest;
import com.baiwang.bop.respose.entity.InvoiceQueryResponse;
import com.baiwang.bop.respose.entity.LoginResponse;

/**
 * 销项——发票查询
 * baiwang.invoice.query（发票查询）
 * •支持发票流水号查询。
 *
 * •支持发票号码、代码查询。
 *
 * •支持开票终端标识查询。
 *
 * •支持开票日期查询。
 *
 * •支持购方信息查询（名称、税号）。
 */
public class invoice_query {

    //公共参数
    public static String url = "http://60.205.83.27/router/rest";             //接口地址
    public static String appkey="10000005";                                   //AppKey
    public static String appSecret="b65025d0-19d2-4841-88f4-ff4439b8da58";    //AppSecrect
    public static String userName="admin_1800000021168";                      //用户名
    public static String password="a123456";                                   //密码
    public static String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值

    public static void main(String[] args) {
       try
        {
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

            InvoiceQueryRequest request = new InvoiceQueryRequest();

            //查询类型 1发票流水号查询  2发票号码和发票代码查询 3纳税人识别号【销方】
            // 4开票终端标识 5开票日期起止  6.购方信息 7.机构代码 9.保单号 10.快递单号 11业务流水号
            request.setInvoiceQueryType("1");//查询类型
            request.setSerialNo("test0kp005");// 1.发票流水号查询
            request.setInvoiceCode("150003521055"); // 2.发票号码和发票代码查询
            request.setInvoiceNo("82597817");//发票号码
            request.setSellerTaxNo("91500000747150426A");// 3.销方纳税人识别号
            request.setInvoiceTerminalCode("kpyuan002"); // 4.开票终端标识
            //request.setInvoiceStartDate("20180919083148"); // 5.开票日期起止 格式：yyyyMMddHHmmss
            //request.setInvoiceEndDate("20170604"); //开票日期止
            request.setBuyerName("百望股份有限公司"); // 6.购方信息
            request.setOrganizationCode(""); // 7.机构代码
            //9.保单号
            request.setTrackNumber(""); // 10.快递单号
            request.setReqSerialNumber("");// 11.业务流水号

            IBopClient client = new BopRestClient(url, appkey, appSecret);
            InvoiceQueryResponse response = client.execute(request, token, InvoiceQueryResponse.class);
        }
        catch (BopException e)
        {
            e.printStackTrace();
        }
    }

}
