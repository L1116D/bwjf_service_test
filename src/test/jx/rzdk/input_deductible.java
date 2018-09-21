package test.jx.rzdk;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.input.DeductibleRequest;
import com.baiwang.bop.request.impl.input.node.InvoiceNode;
import com.baiwang.bop.respose.entity.LoginResponse;
import com.baiwang.bop.respose.entity.input.DeductibleResponse;
import com.baiwang.bop.utils.JacksonUtil;
import test.util.StaticTemp;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证抵扣申请
 * baiwang.input.deductible（认证抵扣申请 ）
 *
 * 批量将所属期的发票进行认证，返回发票的认证结果。
 */
public class input_deductible {
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

        IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
        DeductibleRequest request = new DeductibleRequest();
        request.setTaxNo("1111111111111");//税号
        request.setVersion("1.0");//版本号 1.0
        request.setPeriod("201708");//税控所属期

        List list = new ArrayList<>();
        InvoiceNode node = new InvoiceNode();
        node.setInvoiceCode("1484119141");//发票代码
        node.setInvoiceNumber("84119208");//发票号码
        node.setPurchaserTaxNo("1111111111111");//购方税号
        node.setCertificationType("1");//认证类型：1为抵扣，2为出口退税，3为代理出口退税；不传该字段，则默认为1。认证类型为2或3时，税款所属期必须为自然月
        list.add(node);
        request.setInvoiceList(list);

        try
        {
            DeductibleResponse response = client.execute(request, token, DeductibleResponse.class);
            assert true;
            //log.info(JacksonUtil.beanToString(response));
            //log.info("success");
            System.out.println(JacksonUtil.beanToString(response));
            System.out.println("success");
        }
        catch (BopException e)
        {
            assert false;
            //log.info("error");
            System.out.println("error");
        }


    }
}
