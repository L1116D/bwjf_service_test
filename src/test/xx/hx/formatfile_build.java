package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.FormatfileBuildRequest;
import com.baiwang.bop.respose.entity.FormatfileBuildResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 版式文件生成
 * baiwang.formatfile.build（版式文件生成）
 * 用于版式文件的生成及推送，通过发票代码、发票号码、发票请求流水号生成版式文件并推送，可用于首次生成版式文件或再次推送已生成的版式文件。
 * 注：推送的发票只适用于在百望云存在的电子发票。
 */
 //响应信息：{"method":"baiwang.formatfile.bulid","requestId":"109dc726-700c-4d7b-9813-d626b7453820",
// "response":{"data":"http://123.56.92.221/fp/d?d=99E6EE9B4ACF8A4C30D4BB9561B1D251A2BC630A86E800E824952D936567B300","message":"调用[版式文件上传]接口上传成功！"}}
public class formatfile_build {
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
            //缺少公共方法 ToolUtils
            //String token = ToolUtils.getToken(StaticTemp.getUrl(),StaticTemp.getAppkey(),StaticTemp.getAppSecret(),StaticTemp.getUserName(),StaticTemp.getPassword(),StaticTemp.getUserSalt());

            FormatfileBuildRequest request = new FormatfileBuildRequest();
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setSerialNo("test0kp005");//开票流水号
            request.setInvoiceNo("82597817");//发票号码
            request.setInvoiceCode("150003521055");//发票代码
            request.setPushType("0");//推送标志 0：不推送 1：推送（邮箱电话必填一个）
            request.setBuyerPhone("15117121801");//手机号
            //request.setBuyerEmail("");

            //同步请求
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            FormatfileBuildResponse responsess = client.execute(request, token, FormatfileBuildResponse.class);
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
