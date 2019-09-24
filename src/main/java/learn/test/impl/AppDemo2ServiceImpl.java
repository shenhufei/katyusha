package learn.test.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.Igonre;
import com.shenhufei.Katyusha.anntion.MethodVersion;
import com.shenhufei.Katyusha.anntion.Version;

import learn.test.AppDemo2Service;

@Version("1.0.0")
public class AppDemo2ServiceImpl implements AppDemo2Service {
    public static final Logger logger = LoggerFactory
            .getLogger(AppDemo2ServiceImpl.class);
   
    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(2002)
    public void getStockMessage(String jsonString) {
       
    }

    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(100001)
    public void getLatestVersion(String jsonString) throws Exception {
        
    }

    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(2000)
    public void login(String jsonString) throws Exception {
       
    }

    @Igonre
    public void getUserInfoByBossId() {
        
    }

    @Igonre
    public void getUserInfoByAccount() {
        
    }
}
