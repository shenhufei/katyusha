package learn.importexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MakeDealerData2 {

    public static void main(String[] args) throws Exception {
        MakeDealerData2 mdd = new MakeDealerData2();
        
        mdd.readExcel(new File("D:\\4.1合作渠道新开账户.xls"));
    }
    
    
    public List readExcel(File file) throws Exception {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // 获取区域信息
            String string = null;
            List<ZoneVO>  zoneList = new ArrayList<>();//JSONUtil.parseJsonList(string, ZoneVO.class);
            
            
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            
            StringBuilder mobileBuffer = new StringBuilder();
            StringBuilder idBuffer = new StringBuilder();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                //String password = PasswordHash.createHash("123456");
                String password = "123456";
                for (int i = 1; i < sheet.getRows(); i++) {
                	System.out.print("INSERT INTO(ACCOUNT,MOBILE_PHONE,PSWD,PROVINCE_ID,ZONE_ID,ADMIN_ID,ADMIN_PROVINCE_ID,ADMIN_ZONE_ID,COMPANY_NAME,LEADER_NAME,REGISTER_SOURCE,TYPE,REGISTER_TIME,LAST_UPDATE_TIME,ID_CARD_NUMBER,USER_ADD_SOURCE,COMPANY_LICENSE_NUMBER) VALUES ");
                	/*List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        
                        innerList.add(cellinfo);
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);*/
                    String dealeName = sheet.getCell(0, i).getContents().trim();
                    String leaderName = dealeName;
                    String idcardno = sheet.getCell(1, i).getContents().trim();
                    String mobilePhone = sheet.getCell(3, i).getContents();
                    String cityName = sheet.getCell(4, i).getContents().trim();
                    String personName = sheet.getCell(5, i).getContents().trim();
                    String bankName = sheet.getCell(6, i).getContents().trim();
                    String bankCardNo = sheet.getCell(7, i).getContents().trim();
                    String branchBank = sheet.getCell(8, i).getContents().trim();
                    String accountProvince = sheet.getCell(9, i).getContents().trim();
                    String accountCity = sheet.getCell(10, i).getContents().trim();
                    String provinceId = "";
                    Integer zoneId = 0;
                    Integer accountCityId = 0;
                    //根据区域名称获取省份，区域id信息
                    for (ZoneVO zone : zoneList) {
                        if(cityName.equals(zone.getName())) {
                            zoneId = zone.getId();
                            provinceId = zone.getProvinceId();
                            break;
                        }
                    }
//                    mobileBuffer.append(",").append(mobilePhone);
//                    idBuffer.append(",'").append(idcardno).append("'");
                    
                    
//                    System.out.println(dealeName+","+leaderName+","+mobilePhone+","+password+","+idcardno);
//                    System.out.println("('"+mobilePhone+"','"+mobilePhone+"','"+password+"','"+provinceId+"','"+zoneId+"',null,'"+provinceId+"','"+zoneId+"','"+dealeName+"','"+leaderName+"',1,1,NOW(),NOW(),'"+idcardno+"',2),");
                    //拿到insert 语句的后半段
                    System.out.print("('"+mobilePhone+"','"+mobilePhone+"','"+password+"','"+provinceId+"','"+zoneId+"',1821,'SH',2,'"+dealeName+"','"+leaderName+"',1,1,NOW(),NOW(),'"+idcardno+"',2,'12345678') ;");
                    System.out.println();
                    for (ZoneVO zone : zoneList) {
                        if(accountCity.equals(zone.getName())) {
                        	accountCityId = zone.getId();
                            break;
                        }
                    }
                    
                    System.out.print("INSERT INTO (BUSINESS_ID, BANK_CARD_NO, BANK_ACCOUNT_PERSON_NAME, BANK_ACCOUNT_BANK_NAME, BANK_ACCOUNT_BANK_BRANCH, BANK_ACCOUNT_PROVINCE, BANK_ACCOUNT_CITY, BANK_DEFAULT, CREATE_TIME) VALUES ");
                    System.out.print("(LAST_INSERT_ID(),'"+bankCardNo+"','"+personName+"','"+bankName+"','"+branchBank+"','"+accountProvince+"',"+accountCityId+",1,NOW());");
                
                    System.out.println();
                    
                }
                
                System.out.println();
                System.out.println();
//                System.out.println(mobileBuffer.substring(1));
//                System.out.println(idBuffer.substring(1));
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
