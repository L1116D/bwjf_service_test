package test.yhtx.qygl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceCompanyUpdateApplyRequest;
import com.baiwang.bop.respose.entity.InvoiceCompanyUpdateApplyResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

import static test.util.StaticTemp.*;

/**
 * 企业变更管理
 * baiwang.invoice.admin.update.applyFor（企业变更申请）
 * 企业变更申请
 */
public class invoice_admin_update_applyFor {
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
            InvoiceCompanyUpdateApplyRequest request = new InvoiceCompanyUpdateApplyRequest();
            request.setCompanyFormerNo("110101201611040");//原企业税号
            request.setChangeCompanyNo("110101201611040");//变更后企业税号
//            request.companyFormerNo("110101201611040");
//            request.changeCompanyNo("110101201611040");
            request.setChangeCompanyName("test1232");//变更后名称
            request.setChangeProvince("山西大同");//变更省
            request.setOperator("liqiang");//操作人
            request.setCertificatesFile("file");//加密后资质文件
            request.setProductMark("0");//产品标志，云销项-0 云进项-1 移动开票-2 易票宝-3 发票帮-4 电票通-5 扫码抵扣-6 合规查验-7 开发平台-8 云打印-9 无-1

            IBopClient client = new BopRestClient(url, appkey, appSecret);
            InvoiceCompanyUpdateApplyResponse response = client.execute(request, token, InvoiceCompanyUpdateApplyResponse.class);
        }
        catch (BopException e)
        {
//            log.info(e.getErrCode());
//            log.info(e.getErrMsg());
//            log.info(e.getSubMessage());
//            log.info(e.getSubCode());
//            log.info(e.getMessage());
            System.out.println(e.getErrCode());
            System.out.println(e.getErrMsg());
            System.out.println(e.getSubMessage());
            System.out.println(e.getSubCode());
            System.out.println(e.getMessage());
        }
    }

}
