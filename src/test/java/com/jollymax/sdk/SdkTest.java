package com.jollymax.sdk;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jollymax.sdk.api.Constants;
import com.jollymax.sdk.client.DefaultJollyMaxClient;
import com.jollymax.sdk.client.JollyMaxClient;
import com.jollymax.sdk.config.GlobalMerchantConfig;
import com.jollymax.sdk.config.MerchantConfig;
import com.jollymax.sdk.distribute.req.OrderQueryRequest;
import com.jollymax.sdk.distribute.resp.OrderQueryResponse;
import com.jollymax.sdk.domain.GatewayResult;
import com.jollymax.sdk.enums.Env;
import com.jollymax.sdk.utils.RsaUtils;

/**
 * Hello world!
 */
public class SdkTest {

    private static JollyMaxClient jollyMaxClient;

    @BeforeClass
    public static void init() throws Exception {
        jollyMaxClient = DefaultJollyMaxClient.getInstance();
        jollyMaxClient.setEnv(Env.UAT);
        // or set the other base url
        // client.setBaseUrl("https://the-other-url.jollymax.com/aggregate-pay/api/gateway");

        // init default MerchantConfig

        // String jollymaxPublicKey = "";
        // String merchantPrivateKey = "get your private key from testGenKeyPair()";
        // String merchantNo = "get merchantNo from dashboard";
        // String merchantAppId = "get appId from dashboard";

        String jollymaxPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6V6KhocSuxFbg7EUU3FE9EkB7Ir7p6z8eIKmK7EgORFvoGl6g2ZS2YZM/r5xvHQJVPF0I6XeCQGyVZa5nIJR/EVMP1xx6T06GLuz9tEoJDc9bKBr1xlgsasqmFRc1nuewTorj8j3kZ3uCgJqvIyeEi4ynhkJi1QNcQ/NmgFlasAYSzDAra4qSb7SuhF9BnhNRJeD9XBCv8391Fs3RVdb62iJLPwZgfvQRnGPDTpbCmH8mrOtBCDxQPgwaUiyyG1DZPuoVa7aUopknEV7UIIKlJaDr2gjuCT5YFjSHu+25vbYb/VLZdtsTiKgynq7OQb3YdTyate8RRLIazzjD3DzpQIDAQAB";
        String merchantPrivateKey =
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDpXoqGhxK7EVuDsRRTcUT0SQHsivunrPx4gqYrsSA5EW+gaXqDZlLZhkz+vnG8dAlU8XQjpd4JAbJVlrmcglH8RUw/XHHpPToYu7P20SgkNz1soGvXGWCxqyqYVFzWe57BOiuPyPeRne4KAmq8jJ4SLjKeGQmLVA1xD82aAWVqwBhLMMCtripJvtK6EX0GeE1El4P1cEK/zf3UWzdFV1vraIks/BmB+9BGcY8NOlsKYfyas60EIPFA+DBpSLLIbUNk+6hVrtpSimScRXtQggqUloOvaCO4JPlgWNIe77bm9thv9Utl22xOIqDKers5Bvdh1PJq17xFEshrPOMPcPOlAgMBAAECggEAM6wCQh1AUXlbaQJZfMxUtz4/FdAEPrE3ybsr7fqvJtOsgrk4LkYGzDFHdRRX/KE4u70muQnt6tKcWQUhnfzhCFmTs2fVtQ4ggf91ro3LNJGioR24Iw10coJy0P3E/Jysnx4xLQoTfwOYBFFartr32RCvln/2tAntW/5iyNnMSeFIgQBsWAlQLC9tMxgs2eOpDYVjBqWNM5y4dNV4t+fOz3JP/v1rVHc4b3WqVcf6OaCwCXAdRGQJlfZElHJpTu0zC0qz+X2cHhYPxp6xlyhjM1U1imX0AtOoEppasKQMZPXWn4e7VJxUxtkuHkh7dn/THdzPvq1wChE5P+bRNTE9NQKBgQD2viOJNY2MO0fybGmJsoqDPgGac+9SWh3HttAij+z6a8TR9/caM4bNjbXB5F8FtGB6cbs8uhhebg2nGzdFP0jYP1aWb4qKFuyvqY00S+gpvs/KiknH5QIAn84ayGglC51E1JwIPt2RT21yEWXpKj5B971sLFu5f4KmjGZa2RcKLwKBgQDyH/TCUdVIhTNfNYz+q53/EkY7bwpIdk7qbB7/X4ImPD7nFOLxaCJBNufo1OlgR+TdNQRjJ6BqtNts6B3IDL21ZIZMRbJ10LQhyTNOuuTihBZp1Z2wnXVg1YcuwV9zouRfNEdyUgaiXJSZRW3GYRPwpLeBzq9oBtU/S3UTsCjuawKBgQCWXiuD27oGYr4m08DBZKga8TfC67JGSprpcdSHq5RszC5nEylos0wMirbgkY8DB/jfxd2oVXSyX3k8hE57ieSXvInFJfaUzwGwz7A41aWHgzxYn0v1YO+Gd9z/32/wW5KdNsBcGgunGXOGV2n23YKRayvp92Jyum1hmCBsbu4miwKBgQDW87PRh+D3vvk6f0orFaTwvAKJ5SyV5CJvT4m9YccjtryJXiuT8cTnbJ06QTrm9SyjjdvVQ2rRELr01qUJ4vXQwevQbtfebGhezka0kIt+5ZEYaELUdxWr4CVhRt88w9JHCxyay2OEZPivkcnBIpIXQ8R+g0WJ7vcGeDD6R5wu/QKBgQC0zOuD5k1ceK1A2rtxZTcVJg/mo5rVy4Uh4aTkzHleEtsURws9ynopsCalrlTA2/aL1nZaSrsAvDaYGur8/jH4aFJazjr7ahQLsw4XWmXfkCGhF5I5IqqT9+hc44vKq1YiQdDAB4ZP0JbZgEyf3MVRy94FNzRG/IRBubK5irEKCw==";
        String merchantNo = "010413833582715";
        String merchantAppId = "3a917b2faa5f4d8b91053435c668d857";

        MerchantConfig merchantConfig = MerchantConfig.Builder.builder() //
            .merchantPrivateKey(merchantPrivateKey) //
            .jollymaxPublicKey(jollymaxPublicKey) //
            .merchantNo(merchantNo) //
            .appId(merchantAppId) //
            .build();
        GlobalMerchantConfig.setDefaultConfig(merchantConfig);
        GlobalMerchantConfig.enableDebug();
    }

    @Test(testName = "test generate keypair")
    public void testGenKeyPair() throws Exception {
        Map<String, String> keyPair = RsaUtils.createKeyPair();
        String merchantPrivateKey = keyPair.get(RsaUtils.PRIVATE_KEY_NAME);
        String merchantPublicKey = keyPair.get(RsaUtils.PUBLIC_KEY_NAME);
        System.out.println(merchantPrivateKey);
        System.out.println(merchantPublicKey);
    }

    @Test(testName = "test use default merchant config and use common api mode")
    public void testUseDefaultMerchantConfig() throws Exception {

        // 1.通用api调用 不论服务端是何接口都可以进行调用
        Map<String, Object> request = new HashMap<>();

        request.put("outOrderId", "TEST_WH_20220728_004");
        request.put("messageId", String.valueOf(System.nanoTime()));

        // client构造完成后，可以进行反复使用，不需要重复获取实例
        String result = jollyMaxClient.send(Constants.DISTRIBUTE_ORDER_CREATE, request);
        System.out.println(result);
    }

    @Test(testName = "test query request")
    public void testQueryRequest() throws Exception {

        OrderQueryRequest request = new OrderQueryRequest();
        request.setOutOrderId("xxx");
        GatewayResult<OrderQueryResponse> send = request.send();
        OrderQueryResponse data = send.getData();
        System.out.println(data);
    }

    // @Test(testName = "test use default merchant config and use common api mode")
    // public void testUseDefaultMerchantConfig2() throws Exception {
    // TradeOrderRequest request = new TradeOrderRequest();
    // request.setOutTradeNo("out41254125412");
    // request.setSubject("this is subject");
    // request.setTotalAmount("10000");
    // request.setCurrency("IDR");
    // request.setCountry("ID");
    // request.setUserId("test userId");
    // // set the other information
    // // request.setLanguage();
    // // ......
    //
    // GatewayResult<TradePayOrderResponse> resp = request.send();
    // System.out.println(resp);
    //
    // }

    // @Test(testName = "test has multi merchant configs")
    // public void testUseMultiMerchantConfig() throws Exception {
    // String jsonContent =
    // "{\"outTradeNo\":\"out41254125412\",\"subject\":\"this is
    // subject\",\"totalAmount\":\"10000\",\"currency\":\"IDR\",\"country\":\"ID\",\"userId\":\"userId\",\"language\":\"\",\"reference\":\"\",\"frontCallbackURL\":\"http://www.qq2ddsdfsadfsdfsd.com\",\"notifyUrl\":\"http://www.noticddddasdfasdfe.com\",\"goodsDetails\":[{\"goodsId\":\"D002\",\"goodsName\":\"韩版修身牛仔裤男\",\"quantity\":\"2\",\"price\":\"500\",\"goodsCurrency\":\"IDR\",\"showUrl\":\"http://xxxx.light.png\"}],\"shippingInfo\":{\"firstName\":\"zhang\",\"middleName\":\"shang\",\"lastName\":\"feng\",\"phoneNo\":\"13009090980\",\"email\":\"bacde@ushareit.com\",\"address1\":\"二仙桥\",\"address2\":\"成华大道\",\"city\":\"成都市\",\"region\":\"武侯区\",\"state\":\"州\",\"country\":\"ID\",\"zipCode\":\"000000\"},\"billingInfo\":{\"firstName\":\"账单各\",\"middleName\":\"账单中间名\",\"lastName\":\"账单姓\",\"email\":\"abse@ushareit.com\",\"phone\":\"182927192799\",\"address1\":\"账单地址1\",\"address2\":\"账单地址2\",\"city\":\"账单地址所在城市\",\"region\":\"账单地址所在区域\",\"state\":\"账单地址所在州\",\"country\":\"地址\",\"zipCode\":\"710603\"}}";
    // TradeOrderRequest request = JSON.parseObject(jsonContent, TradeOrderRequest.class);
    //
    // Map<String, String> keyPair = RsaUtils.createKeyPair();
    // String merchantPrivateKey = keyPair.get(RsaUtils.PRIVATE_KEY_NAME);
    // String jollymaxPublicKey = "get jollymax public key from dashboard";
    // String merchantNo = "the other merchant no";
    // String appId = "get appId from dashboard";
    // // In ISV mode, spMerchantNo and merchantAuthToken is required
    // String spMerchantNo = "get spMerchantNo from dashboard";
    // String merchantAuthToken = "get merchantAuthToken from dashboard";
    // MerchantConfig merchantConfig = MerchantConfig.Builder.builder().merchantPrivateKey(merchantPrivateKey)
    // .jollymaxPublicKey(jollymaxPublicKey).merchantNo(merchantNo).appId(appId)
    // // In ISV mode, spMerchantNo and merchantAuthToken is required
    // .spMerchantNo(spMerchantNo).merchantAuthToken(merchantAuthToken).build();
    // GlobalMerchantConfig.addConfig(merchantConfig);
    //
    // GatewayResult<TradePayOrderResponse> resp = request.send(merchantNo);
    // System.out.println(resp);
    // }
}
