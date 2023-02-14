# JollyMax Distribute Server sdk

```xml

<dependency>
    <groupId>com.jollymax</groupId>
    <artifactId>jollymax-server-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Init Config

```java
JollyMaxClient client=DefaultJollyMaxClient.getInstance();
        client.setEnv(Env.UAT);
// or set the other base url
// client.setBaseUrl("https://the-other-url.jollymax.com/aggregate-pay/api/gateway");
```

### If you have only one merchant

```java
        String merchantPrivateKey="get your private key from testGenKeyPair()";
        String jollyMaxPublicKey="get jollymax public key from dashboard";
        String merchantNo="get merchantNo from dashboard";
        String appId="get appId from dashboard";

        MerchantConfig merchantConfig=MerchantConfig.Builder.builder()
        .merchantPrivateKey(merchantPrivateKey)
        .jollyMaxPublicKey(jollyMaxPublicKey)
        .merchantNo(merchantNo)
        .appId(appId)
        .build();
        GlobalMerchantConfig.setDefaultConfig(merchantConfig);
```

### If you have multi merchants

```java
Map<String, String> keyPair=RsaUtils.createKeyPair();
        String merchantPrivateKey=keyPair.get(RsaUtils.PRIVATE_KEY_FILE);
        String jollyMaxPublicKey="get jollymax public key from dashboard";
        String merchantNo="the other merchant no";
        String appId="get appId from dashboard";

        //In ISV mode, spMerchantNo and merchantAuthToken is required
        String spMerchantNo="get spMerchantNo from dashboard";
        String merchantAuthToken="get merchantAuthToken from dashboard";

        MerchantConfig merchantConfig=MerchantConfig.Builder.builder()
        .merchantPrivateKey(merchantPrivateKey)
        .jollyMaxPublicKey(jollyMaxPublicKey)
        .merchantNo(merchantNo)
        .appId(appId)
        .build();
        GlobalMerchantConfig.addConfig(merchantConfig);
// MerchantConfig merchantConfig2 = ....
// GlobalMerchantConfig.addConfig(merchantConfig2);
// MerchantConfig merchantConfig3 = ....
// GlobalMerchantConfig.addConfig(merchantConfig3);
```

## Send request

### Use json api

```java

String json="{\"code\":\"your product code\",\"messageId\":\"29128914605625\",\"outOrderId\":\"TEST_WH_20220728_004\",\"quantity\":\"1\",\"tradeInfo\":{\"userId\":\"123456\"}}";
        JSONObject jsonObject=JSON.parseObject(json);

// client构造完成后，可以进行反复使用，不需要重复获取实例
// send with default merchant config        
        String result=client.send("distribute-order-create",jsonObject);
```

### Use easy request api

```java
OrderCreateRequest request = new OrderCreateRequest();
request.setOutOrderId("TEST_WH_20220728_004");
request.setMessageId(String.valueOf(System.nanoTime()));
request.setCode("your product code");
request.setQuantity("1");
// 设置充值账号信息
Map<String, Object> tradeInfo = new HashMap<>();
tradeInfo.put("userId", "123456");
request.setTradeInfo(tradeInfo);
// set the other information
// request.setLanguage();
// ......
// send with default merchant config        
        GatewayResult<TradePayOrderResponse> resp=request.send();
```

### Use specify merchant config

```java
client.send("merchant no");
```

## Verify Notification

```java
String body="";// from http post body
        String sign="";// from http header, header name is 'sign'        
        boolean result=client.verifyNotification(body,sign);
```
