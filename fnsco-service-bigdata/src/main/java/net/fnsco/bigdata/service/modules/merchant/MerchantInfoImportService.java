package net.fnsco.bigdata.service.modules.merchant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.fnsco.bigdata.api.dto.WebMerchantTerminalDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.ReadExcel;


/**@desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoImportService{
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private MerchantPosService merchantPosService;
	//批量导入客户
	@Transactional
	public boolean batchImport(String name,MultipartFile file){
		boolean b=false;
		//创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
        List<Object[]> customerList = readExcel.getExcelInfo(name ,file);
        if(customerList.size()!=0){
        	//迭代添加信息（注：实际上这里也可以直接将cpolicyList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
		for(Object[] objs:customerList){
				String innerCode = merchantCoreService.getInnerCode();
        	   /**
        	    * 商户基本信息
        	    */
        	   MerchantCore merchantCore = new MerchantCore();
        	   merchantCore.setInnerCode(innerCode);
        	   merchantCore.setMerName((String)objs[0]);
        	   merchantCore.setBusinessLicenseNum((String)objs[1]);
        	   merchantCore.setLegalValidCardType("0");
        	   String aa=String.valueOf(objs[2]);
        	   merchantCore.setCardNum(aa);
        	   merchantCore.setLegalPerson((String)objs[3]);
        	   merchantCore.setAbbreviation((String)objs[4]);
        	   //商户基本信息保存
        	   merchantCoreService.doAddMerCore(merchantCore);
        	  /**
        	    * 商戶pos机信息
        	    */
        	   MerchantPos merchantPos=new MerchantPos();
        	   merchantPos.setInnerCode(innerCode);
        	   merchantPos.setMercReferName((String)objs[5]);
        	   merchantPos.setPosType((String)objs[11]);
        	   merchantPos.setSnCode((String)objs[12]);
        	   merchantPos.setPosAddr((String)objs[15]);
        	   //pos机信息保存
        	   merchantPosService.insert(merchantPos);
        	   /**
        	    * 商户银行卡信息
        	    */
        	   MerchantBank merchantBank=new MerchantBank();
        	   merchantBank.setInnerCode(innerCode);
        	   merchantBank.setAccountName((String)objs[16]);
        	   String ab=String.valueOf(objs[17]);
        	   merchantBank.setAccountNo(ab);
        	   merchantBank.setSubBankName((String)objs[18]);
        	   List<MerchantBank> bankList=new ArrayList<MerchantBank>();
        	   bankList.add(merchantBank);
        	   //银行卡信息保存
        	   merchantCoreService.doAddMerBanks(bankList);
        	   /**
        	    * 商户终端信息
        	    */
        	   MerchantTerminal merchantTerminal=new MerchantTerminal();
        	   merchantTerminal.setInnerCode(innerCode);
        	   //借记卡字符串分割
        	   String jjk0=String.valueOf(objs[20]);
        	   //借记卡转小数
        	   if("null".equals(jjk0)) {
        		   merchantTerminal.setDebitCardRate(null);
        		   merchantTerminal.setDebitCardMaxFee(null);
        	   }else {
        		   String jjk1=jjk0.substring(0, jjk0.indexOf("%"));
        		   BigDecimal bigDecimal0= new BigDecimal(jjk1);
        		   BigDecimal jjk=  bigDecimal0.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
        		   merchantTerminal.setDebitCardRate(String.valueOf(jjk.doubleValue()));
        		   //借记卡费率封顶值
        		   String max1=jjk0.substring(jjk0.indexOf("+"));
        		   Integer max=Integer.valueOf(max1);
        		   merchantTerminal.setDebitCardMaxFee(max);
        	   }
        	   String hjk=String.valueOf(objs[21]);
        	   merchantTerminal.setCreditCardRate(hjk);
        	   //支付宝微信费率分割
        	   String xx=String.valueOf(objs[23]);
        	   if("null".equals(xx)) {
        		   merchantTerminal.setAlipayFee(null);
        		   merchantTerminal.setWechatFee(null);
        	   }else {
        		   //支付宝费率转换
        		   String zfb1=xx.substring(xx.indexOf("支付宝")+3, xx.indexOf("%"));
        		   BigDecimal bigDecimal1= new BigDecimal(zfb1);
        		   BigDecimal zfb=  bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
        		   merchantTerminal.setAlipayFee(String.valueOf(zfb.doubleValue()));
        		   //微信费率转换
        		   String wx1=xx.substring(xx.indexOf("微信")+2, xx.lastIndexOf("%"));
        		   BigDecimal bigDecimal2= new BigDecimal(wx1);
        		   BigDecimal wx=  bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
        		   merchantTerminal.setWechatFee(String.valueOf(wx.doubleValue()));
        	   }
        	   List<MerchantTerminal> terminalList=new ArrayList<MerchantTerminal>();
        	   terminalList.add(merchantTerminal);
        	   //终端信息保存
        	   merchantCoreService.doAddMerTerminal(terminalList);
        	   /*if(ab==null||(String)objs[1]==null||(String)objs[5]==null||ac==null||ad==null||ae==null)
        	   {
        		   b = false;
        		   return b;
        	   }*/
        	   }
            b = true;
        }
        return b;
    }
}
