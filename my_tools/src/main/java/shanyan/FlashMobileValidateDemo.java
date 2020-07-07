package shanyan;

import com.alibaba.fastjson.JSONObject;
import shanyan.utils.OkHttpUtil;
import shanyan.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 本机号校验功能：验证手机号demo
 **/
public class FlashMobileValidateDemo {

    // 本机号校验url
    public static final String FLASH_VALIDATE_URL = "https://api.253.com/open/flashsdk/mobile-validate";

    public static void main(String[] args) {
        //应用对应的闪验APPID
        String appId = "";
        //应用对应的闪验APPKEY
        String appKey = "";
        //从SDK获取的token参数
        String token = "";
		//待校验的手机号码
        String mobile = "";
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("token", token);
            params.put("appId", appId);
			params.put("mobile", mobile);
            params.put("sign", SignUtils.getSign(params, appKey));
            JSONObject jsonObject = OkHttpUtil.postRequest(FLASH_VALIDATE_URL, params);
            if (null != jsonObject) {
                System.out.println("response:"+jsonObject.toJSONString());
                String code = jsonObject.getString("code");     //返回码 200000为成功
                String message = jsonObject.getString("message");//返回消息
                String chargeStatus = jsonObject.getString("chargeStatus"); //是否收费
                if ("200000".equals(code)) {
                    String dataStr = jsonObject.getString("data");
                    JSONObject dataObj = JSONObject.parseObject(dataStr);
                    String isVerify = dataObj.getString("isVerify");
                    String tradeNo = dataObj.getString("tradeNo");//交易流水号
                    if ("1".equals(isVerify)) {
                        System.out.println("手机号码校验通过");
                    } else if ("0".equals(isVerify)) {
                        System.out.println("手机号码校验不通过");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
