package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceCombinationQueryRequest;
import com.baiwang.bop.respose.entity.InvoiceCombinationQueryResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

/**
 * 发票组合查询
 * baiwang.invoice.queryCombination（发票组合查询）
 *
 * 根据校验条件查询发票信息，返回发票列表，可分页。
 * （注：为了效率起见，查询最多返回100条数据）
 *
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
public class invoice_queryCombination {
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
            InvoiceCombinationQueryRequest request = new InvoiceCombinationQueryRequest();
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setReturnType("1");  // 返回数据标志
            //TODO test
            request.setInvoiceStartDate("20180919000");  //开票日期起
            //request.setInvoiceEndDate("201809310000");    //开票日期止
//            request.setInvoiceStartDate("201702010000");  //开票日期起
//            request.setInvoiceEndDate("201802310000");    //开票日期止
            request.setSourceSign("00");  //发票来源(来源标志 01 接口开具 02已开上传 03 开票申请单 04 流水单 05 接口待开上传 06 销方待开导入 07 购方待开导入 08 非百望云开具)
            //request.setInvoiceType("05");  //发票状态 00 02 03 05
            //request.setInvoiceType("02");
            //request.setInvoiceType("03");
            request.setDeviceType("0");  //设备类型
            //发票类型代码
            request.setPage("6");     //页码
            request.setSize("1");     //每页条数
            request.setMail("");      //邮件
            request.setTelePhone("18210603023");  //电话

            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            InvoiceCombinationQueryResponse response = client.execute(request, token, InvoiceCombinationQueryResponse.class);
            System.out.println(response);
        }
        catch (BopException e)
        {
            e.printStackTrace();
        }


    }




}
