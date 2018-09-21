package test.xx.djgl;

import com.baiwang.bop.client.BopException;
import com.baiwang.bop.client.IBopClient;
import com.baiwang.bop.client.ILoginClient;
import com.baiwang.bop.client.impl.BopRestClient;
import com.baiwang.bop.client.impl.PostLogin;
import com.baiwang.bop.request.impl.LoginRequest;
import com.baiwang.bop.request.impl.invoice.common.InvoiceDetails;
import com.baiwang.bop.request.impl.invoice.impl.InvoiceUploadRequest;
import com.baiwang.bop.respose.entity.InvoiceUploadResponse;
import com.baiwang.bop.respose.entity.LoginResponse;
import test.util.StaticTemp;

import java.util.ArrayList;
import java.util.List;

/**
 * 发票上传
 * baiwang.invoice.upload（发票上传）
 */
public class invoice_upload {
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
            IBopClient client = new BopRestClient(StaticTemp.getUrl(), StaticTemp.getAppkey(), StaticTemp.getAppSecret());
            InvoiceUploadRequest request = new InvoiceUploadRequest();
            request.setDeviceType("11");
            request.setSellerTaxNo("91500000747150426A");//销方纳税人识别号
            request.setInvoiceUploadType("3");//上传类型 1:预制发票数据上传(扫码开票,无购方四要素) 2：预制发票上传 3：已开发票上传 4、已开发票作废上传
            request.setInvoiceTypeCode("026");//发票种类编码: 004:增值税专用发票，007:增值税普通发票， 026：增值税电子发票，025：增值税卷式发票
            request.setInvoiceSpecialMark("00");//特殊票种标记，00：普通发票 02：农业发票 06：抵扣通行费 07：其他通行费 08：成品油  默认：00
            request.setInvoiceDate("20180919083148");
            request.setInvoiceNo("82597817");//发票号码
            request.setInvoiceCode("150003521055");//发票代码
            request.setInvoiceListMark("0");//清单标志： 0：无清单 1 ：带清单 发票明细大于等于8行必须带清单）
            request.setInvoiceType("0");//开票类型0:正数发票（蓝票） 1：负数发票（红票）
            //TODO 没有商品性质 ？？
            //goodsLineNature
            //request.set

//----- test ---- start -----


//            private String machineNo;
//            private String autoOpen;
//            private String pushChannelFlag;
//            private String appid;
//            private String organizationName;
//            private String invoiceDate;
//            private String invoiceClosingDate;
//            private String returnType;
//            private String taxControlCode;
//            private String invoiceCheckCode;
//            private String invoiceQrCode;
//            private String buyerEmail;
//            private String buyerPhone;
//            private String sellerTaxNo;
//            private String sellerName;
//            private String sellerAddressPhone;
//            private String sellerBankAccount;
//            private String organizationCode;
//            private String serialNo;
//            private String invoiceSpecialMark;
//            private String invoiceTypeCode;
//            private String invoiceTerminalCode;
//            private String buyerTaxNo;
//            private String buyerName;
//            private String buyerAddressPhone;
//            private String buyerBankAccount;
//            private String drawer;
//            private String checker;
//            private String payee;
//            private String invoiceType;
//            private String redInfoNo;
//            private String originalInvoiceCode;
//            private String originalInvoiceNo;
//            private String taxationMode;
//            private String deductibleAmount;
//            private String invoiceTotalPrice;
//            private String invoiceTotalTax;
//            private String invoiceTotalPriceTax;
//            private String signatureParameter;
//            private String taxDiskNo;
//            private String taxDiskKey;
//            private String taxDiskPassword;
//            private String goodsCodeVersion;
//            private String consolidatedTaxRate;
//            private String notificationNo;
//            private String remarks;
//            private String reqSerialNumber;





//------ test --- end ----




            // ...
            List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();
            InvoiceDetails details = new InvoiceDetails();
            details.setGoodsCode("10341");
            details.setGoodsName("goodsname");
            details.setGoodsTotalPrice("1.55");
            details.setGoodsTotalTax("0.45");
            details.setGoodsTaxRate("0.34");
            details.setPriceTaxMark("1");
            details.setPreferentialMark("0");
            invoiceDetailsList.add(details);
            request.setInvoiceDetailsList(invoiceDetailsList);
            InvoiceUploadResponse response = client.execute(request, token, InvoiceUploadResponse.class);
        }
        catch (BopException e)
        {
//            log.info("访问失败");
//            log.info(e.getErrCode());
//            log.info(e.getErrMsg());
//            log.info(e.getSubMessage());
//            log.info(e.getSubCode());
//            log.info(e.getMessage());
            System.out.println("访问失败");
            System.out.println(e.getErrCode());
            System.out.println(e.getErrMsg());
            System.out.println(e.getMessage());
            System.out.println(e.getSubCode());
        }



    }
}
