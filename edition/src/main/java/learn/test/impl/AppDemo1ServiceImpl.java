package learn.test.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.MethodVersion;
import com.shenhufei.Katyusha.anntion.Version;

import learn.test.AppDemo1Service;

@Version("1.0.0")
public class AppDemo1ServiceImpl implements AppDemo1Service {
    public static final Logger logger = LoggerFactory
            .getLogger(AppDemo1ServiceImpl.class);

   

    /*
     * 入库详情
     */
    @Override
    @MethodVersion({"1.0.0", "1.0.1" })
    @Code(3011)
    public void getEnterStockTaskDetail(String jsonString)
            throws ParseException {
        
    }

    /**
     * 盘库详情接口 json参数中的stockId为盘库任务ID
     */
    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(3021)
    public void getStockCheckTaskDetail(String jsonString)
            throws ParseException {
        System.out.println("3021方法执行了");
    }

    /**
     * 入库任务列表
     */
    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(3010)
    public void getEnterStockTaskList(
            String jsonString) {
        
    }

    /*
     * 盘库任务列表
     */
    @Override
    @MethodVersion({ "1.0.0", "1.0.1" })
    @Code(3020)
    public void getStockCheckTaskList(
            String jsonString) {
        
    }

    /*
     * 入库任务状态更新
     */
    @Override
    @Code(3012)
    @MethodVersion({ "1.0.0", "1.0.1" })
    public void updateEnterStockStatus(String jsonString) {
       

    }

    /*
     * 盘库状态更新+盘库报警信息
     */
    @Override
    @Code(3022)
    @MethodVersion({ "1.0.0", "1.0.1" })
    public void updateCheckStockStatus(String jsonString) {
       

    }

    /**
     * 获取统计信息
     * 
     * @author shenhufei
     *
     * @param jsonString
     * @return
     */
    @Override
    @Code(3000)
    @MethodVersion({ "1.0.0", "1.0.1" })
    public void getStockTaskStatistics(String jsonString) {
        

    }
}
