package shanyan;

import com.alibaba.fastjson.JSONObject;
import shanyan.utils.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 一键登录功能：token置换手机号demo
 **/
public class FlashMobileLoginDemo {

    // 免密登录后台url
    public static final String FLASH_LOGIN_URL = "https://api.253.com/open/flashsdk/mobile-query";

    //创建应用时填入的rsa公钥对应的私钥字符串
    public static final String privateKey = "";


    public static void main(String[] args) {
        //应用对应的闪验APPID
        String appId = "ZQZSp7C0";
        //应用对应的闪验APPKEY
        String appKey = "FLASH_IOS_APP_KEY";
        //手机号加解密方式 0 AES 1 RSA , 可以不传，不传则手机号解密直接使用AES解密
        //从SDK获取的token参数
        String token = "A2-TKpAsI4crP_K7pG6slmdZkya10_4SHultwgFbohS9kV1LOsGVoThu-yoVAT-SHTJWfP1e7W2bTCbvPKVzScR4P5kOn0JOAGXYI6vm5BYXuZ9_k9mD3J8IpAPtp_Z-euu7b2BzEqMTUni9LCyAWIZXKzcXG06MqBXJZnlfKdeLh-TvjL16FJDbLksfo_JcDMR2-RDARUxXuM3RhUX2gO-83ub4znX-djPjV3Po0Qq9L2gK18yqcTJ0YL3MwMHDyo5";
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("token", token);
            params.put("appId", appId);
            params.put("sign", SignUtils.getSign(params, appKey));
            JSONObject jsonObject = OkHttpUtil.postRequest(FLASH_LOGIN_URL, params);
            if (null != jsonObject) {
                System.out.println("response:" + jsonObject.toJSONString());
                String code = jsonObject.getString("code");     //返回码 200000为成功
                String message = jsonObject.getString("message");//返回消息
                String chargeStatus = jsonObject.getString("chargeStatus"); //是否收费
                if ("200000".equals(code)) {
                    String dataStr = jsonObject.getString("data");
                    JSONObject dataObj = JSONObject.parseObject(dataStr);
                    String mobile = dataObj.getString("mobileName");
                    String tradeNo = dataObj.getString("tradeNo");//交易流水号
                    /*if ("0".equals(encryptType)) {
                        String key = MD5.getMd5Code(appKey);
                        mobile = AESUtils.decrypt(mobile, key.substring(0, 16), key.substring(16));
                    } else if ("1".equals(encryptType)) {
                        mobile = RSAUtils.decryptByPrivateKeyForLongStr(mobile, privateKey);
                    }*/
                    mobile = RSAUtils.decryptByPrivateKeyForLongStr(mobile, privateKey);
                    System.out.println("mobile:" + mobile); //解密后的手机号码
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
