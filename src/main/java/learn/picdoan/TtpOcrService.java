package learn.picdoan;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.VehicleLicenseOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.VehicleLicenseOCRResponse;

public class TtpOcrService {
	
	private static final Logger logger = LoggerFactory.getLogger(TtpOcrService.class);
	
	private String secretId  = "AKIDzoV0VcrLxWuwvxt261lMfw53c0FOlbzL";
    private String secretKey = "KKBTdKyWvAE6JOIvUtG8sRs9GD3mQ3c6";
    private static OcrClient ocrClient;
    public TtpOcrService(String secretId,String secretKey){
    	this.secretId=secretId;
    	this.secretKey=secretKey;
    }
    
    public TtpOcrService(){
    	
    }
    
    public  static void vehicleLicenseBase64OCR(String base64) {
    	String params = null;
        if (base64 != null) {
            params = "{\"ImageBase64\":\"" + base64 + "\"}";
        }
        VehicleLicenseOCRResponse tencentVehicleLicenseOCR = tencentVehicleLicenseOCR(params);
    }
    
    /**
     * 通过url的图片识别信息
     *
     * @param url 图片的url地址
     * @return 行驶证信息
     * @throws Exception 
     */
    public String vehicleLicenseUrlOCR(String url) {
    	String params = new String();
    	if (url != null) {
            params = "{\"ImageUrl\":\"" + url + "\"}";
        }
        VehicleLicenseOCRResponse oCR = tencentVehicleLicenseOCR(params);
        if(null!=oCR){
        	return oCR.getFrontInfo().getVin();
        }
        return null;
    }

    private  static VehicleLicenseOCRResponse tencentVehicleLicenseOCR(String params)  {
        VehicleLicenseOCRRequest req = VehicleLicenseOCRRequest.fromJsonString(params, VehicleLicenseOCRRequest.class);
        OcrClient client = getClient();
        try {
            return client.VehicleLicenseOCR(req);
        } catch (Exception e) {
        	logger.error("图片url是"+params+"调用ocr是被失败");
        }
		return null;
    }

    private static OcrClient getClient() {
        if (ocrClient != null) {
            return ocrClient;
        }
            Credential cred = new Credential("AKIDzoV0VcrLxWuwvxt261lMfw53c0FOlbzL", "KKBTdKyWvAE6JOIvUtG8sRs9GD3mQ3c6");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            ocrClient = new OcrClient(cred, "ap-shanghai", clientProfile);
            return ocrClient;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public static void main(String[] args) {
    	System.out.println(System.getProperty("file.encoding"));
    	System.out.println(Charset.defaultCharset());
	}
}
