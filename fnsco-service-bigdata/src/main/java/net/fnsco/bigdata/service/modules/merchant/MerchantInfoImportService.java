package net.fnsco.bigdata.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.utils.ReadExcel;


/**@desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoImportService{
	@Autowired
	private MerchantCoreService merchantCoreService;
	//批量导入客户
	@Transactional
	public boolean batchImport(String name,MultipartFile file){
        boolean b = false;
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
        List<Object[]> customerList = readExcel.getExcelInfo(name ,file);
        if(customerList.size()!=0){
        	//迭代添加信息（注：实际上这里也可以直接将cpolicyList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
           for(Object[] objs:customerList){
        	   MerchantCore merchantCore = new MerchantCore();
        	  /* Integer aa = new Long((long) objs[0]).intValue();
        	   merchantCore.setId(aa);*/
        	   String ab=String.valueOf(objs[1]);
        	   merchantCore.setInnerCode(ab);
        	   merchantCore.setMerName((String)objs[2]);
        	   merchantCore.setAbbreviation((String)objs[3]);
        	   merchantCore.setEnName((String)objs[4]);
        	   merchantCore.setSignDate((String)objs[5]);
        	   merchantCore.setLegalPerson((String)objs[6]);
        	   String ac=String.valueOf(objs[7]);
        	   merchantCore.setLegalPersonMobile(ac);
        	   merchantCore.setLegalPersonTel((String)objs[8]);
        	   String ad=String.valueOf(objs[8]);
        	   merchantCore.setLegalValidCardType(ad);
        	   String ae=String.valueOf(objs[9]);
        	   merchantCore.setCardNum(ae);
        	   merchantCore.setCardValidTime((String)objs[11]);
        	   String af=String.valueOf(objs[12]);
        	   merchantCore.setBusinessLicenseNum(af);
        	   merchantCore.setBusinessLicenseValidTime((String)objs[13]);
        	   merchantCore.setTaxRegistCode((String)objs[14]);
        	   merchantCore.setRegistAddress((String)objs[15]);
        	   merchantCore.setMercFlag((String)objs[16]);
        	   Integer ag = new Long((long) objs[17]).intValue();
        	   merchantCore.setAgentId(ag);
        	   /*for(int i=0;i<objs.length;i++) {
        		  merchantCore.setId((Integer)objs[i]);
        	   }*/
        	   merchantCoreService.doAddMerCore(merchantCore);
         }
            b = true;
        }
        return b;
    }
}
