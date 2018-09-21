package test.xx.hx;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.common.InvoiceDetails;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceOpenRequest;
import com.baiwang.bop.respose.entity.InvoiceOpenResponse;
import com.baiwang.bop.respose.entity.LoginResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 销项——开票开具
 * baiwang.invoice.open（发票开具）
 */
public class invoice_open {


    public static String url = "http://60.205.83.27/router/rest";             //接口地址
    public static String appkey="10000005";                                   //AppKey
    public static String appSecret="b65025d0-19d2-4841-88f4-ff4439b8da58";    //AppSecrect
    //	public static String userName="admin_1800000021168";                      //用户名
//	public static String password="123456";                                   //密码
    public static String userName="admin_1800000021168";                      //用户名
    public static String password="a123456";                                   //密码
    public static String userSalt="94db610c5c3049df8da3e9ac91390015";         //盐值

    public static void main(String[] args) {
        try
        {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setAppkey(appkey);
            loginRequest.setAppSecret(appSecret);
            loginRequest.setUserName(userName);
            loginRequest.setPasswordMd5(password);
            loginRequest.setUserSalt(userSalt);

            ILoginClient loginClient = new PostLogin(url);
            LoginResponse loginResponse = loginClient.login(loginRequest);

            String token=loginResponse.getAccess_token();//获取token

            InvoiceOpenRequest request = new InvoiceOpenRequest();
            request.setSellerTaxNo("91500000747150426A");//	销方单位税号
            request.setDeviceType("1");//设备类型 0税控服务器，1税控盘
            request.setOrganizationCode("");
            request.setSerialNo("test0kp005");//开票流水号，唯一标志开票请求。支持数字字母下划线组合。//test0kp005  [001-005]
            request.setInvoiceSpecialMark("00");
            //发票种类编码，004:增值税专用发票，007:增值税普通发票，026：增值税电子发票，025：增值税卷式发票
            request.setInvoiceTypeCode("026");
            request.setInvoiceTerminalCode("kpyuan002");//开票点编码
            request.setBuyerTaxNo("");
            request.setBuyerName("百望股份有限公司");
            request.setBuyerAddressPhone("");
            request.setBuyerBankAccount("");
            request.setDrawer("wwm");
            request.setChecker("");
            request.setPayee("");
            request.setInvoiceType("0");
            request.setInvoiceListMark("0");
            request.setRedInfoNo("");
            request.setOriginalInvoiceCode("");
            request.setOriginalInvoiceNo("");
            request.setTaxationMode("0");
            request.setDeductibleAmount("");
            request.setInvoiceTotalPrice("200.00");
            request.setInvoiceTotalTax("34.00");
            request.setInvoiceTotalPriceTax("234.00");
            request.setSignatureParameter("0000004282000000");
            request.setTaxDiskNo("");
            request.setTaxDiskKey("");
            request.setTaxDiskPassword("");
            request.setGoodsCodeVersion("");
            request.setConsolidatedTaxRate("");
            request.setNotificationNo("");
            request.setRemarks("备注");
            // request.setAutoSplit(true);   /** AutoSplit=true时，返回为list，见下方Response示例 **/

            List<InvoiceDetails> invoiceDetailsList = new ArrayList<InvoiceDetails>();
            InvoiceDetails invoiceDetails = new InvoiceDetails();
            invoiceDetails.setGoodsLineNo("1");
            invoiceDetails.setGoodsLineNature("0");
            invoiceDetails.setGoodsCode("1020101000000000000");
            invoiceDetails.setGoodsExtendCode("");
            invoiceDetails.setGoodsName("ww");
            invoiceDetails.setGoodsTaxItem("");
            invoiceDetails.setGoodsSpecification("");
            invoiceDetails.setGoodsUnit("");
            invoiceDetails.setGoodsQuantity("");
            invoiceDetails.setGoodsPrice("");
            invoiceDetails.setGoodsTotalPrice("200.00");
            invoiceDetails.setGoodsTotalTax("34.00");
            invoiceDetails.setGoodsTaxRate("0.17");//税额
            invoiceDetails.setGoodsDiscountLineNo("");
            invoiceDetails.setPriceTaxMark("0");
            invoiceDetails.setVatSpecialManagement("");
            invoiceDetails.setFreeTaxMark("");
            invoiceDetails.setPreferentialMark("0");
            invoiceDetailsList.add(invoiceDetails);

            request.setInvoiceDetailsList(invoiceDetailsList);

            IBopClient client = new BopRestClient(url, appkey, appSecret);

            InvoiceOpenResponse response = 	client.execute(request, token, InvoiceOpenResponse.class);
            // autoSplit = true时, 使用 InvoiceOpenAutoSplitResponse 接收
//		         InvoiceOpenAutoSplitResponse response =
//		             client.execute(request, token, InvoiceOpenAutoSplitResponse.class);
        }
        catch (BopException e)
        {
//		    log.info(e.getErrCode());
//		    log.info(e.getErrMsg());
//		    log.info(e.getSubMessage());
//		    log.info(c);
//		    log.info(e.getMessage());

            System.out.println(e.getErrCode());
            System.out.println(e.getErrMsg());
            System.out.println(e.getSubMessage());
            System.out.println(e.getSubMessage());
            System.out.println(e.getMessage());


        }

    }


}
