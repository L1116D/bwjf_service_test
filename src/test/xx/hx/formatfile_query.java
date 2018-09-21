package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.FormatfileQueryRequest;
import com.baiwang.bop.respose.entity.FormatfileQueryResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 版式文件查询
 * baiwang.formatfile.query（版式文件查询）
 * 根据发票代码、发票号码，或者发票流水号，查询版式文件下载路径。
 */
public class formatfile_query {
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
            /**
             * 1、登录授权获取Token
             * 2、组装请求类
             * 3、发送请求获取返回值
             */
           // String token = ToolUtils.getToken(url,appkey,appSecret,userName,password,userSalt);

            FormatfileQueryRequest request = new FormatfileQueryRequest();
            request.setInvoiceQueryType("0");//查询类型 0：发票代码号码 1：发票流水号 2：保单号
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setSerialNo("test0kp005");//开票流水号
            request.setInvoiceNo("82597817");//发票号码
            request.setInvoiceCode("150003521055");//发票代码
            request.setReturnType("3");// 返回类型 1：URL 2：文件流 3：H5链接

            //同步请求
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            FormatfileQueryResponse response = client.execute(request, token, FormatfileQueryResponse.class);
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
