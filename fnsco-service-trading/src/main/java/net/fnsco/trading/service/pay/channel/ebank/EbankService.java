package net.fnsco.trading.service.pay.channel.ebank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.StringUtil;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4028ReqDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4028RespBodyDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4028RespDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4028ResultDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4029ReqDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4031DTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4032ReqBodyDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4032ReqDTO;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4033ResultDTO;
import net.fnsco.trading.service.pay.channel.ebank.entity.E4029Entity;
import net.fnsco.trading.service.pay.channel.ebank.entity.E4032Entity;

@Service
public class EbankService extends BaseService {

	private String yqdm = "00901079800000088000";// 银企代码,外联客户号---后续配置
	private String AGREE_NO = "Y000136249";// 协议号---后续配置
	private String SrcAccNo = "11014727214006";// 企业账号---后续配置
	private String SrcAccName = "平安测试六零零零四一六零八四九一";// 企业收款户名 ---后续配置
	private String BusiType = "M8PAK";// 费项代码
	private String serverIp = "localhost";// ---后续配置
	private Integer iPort = 7072;

	/**
	 * 付款人协议查询
	 * 
	 * @param AccNo:银行卡号
	 * @return
	 */
	public ResultDTO E4028Trade(String AccNo) {

		try {
			E4028ResultDTO e4028ResultDTO = E4028Query(AccNo);

			// 判断：如果成功，则可以进行付款交易
			if (e4028ResultDTO.isSuccess()) {

				// for (E4028RespBodyDTO e4028RespBodyDTO :
				// e4028ResultDTO.getE4028RespDTO().getList()) {
				// System.out.println(e4028RespBodyDTO.toString());
				// }
			}
			// 判断：如果协议不存在，则发送协议维护(4029)

			// 如果协议状态为待认证或停用，则发起认证(4031)

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 付款人协议查询,主体方法
	 * 
	 * @param AccNo:银行卡号
	 * @return
	 */
	public E4028ResultDTO E4028Query(String AccNo) throws Exception {
		Integer inte = 0;
		String bsnCode = "4028";
		E4028ReqDTO e4028ReqDTO = new E4028ReqDTO();
		e4028ReqDTO.setSrcAccNo(SrcAccNo);
		e4028ReqDTO.setAGREE_NO(AGREE_NO);
		e4028ReqDTO.setBusiType(BusiType);
		e4028ReqDTO.setOppAccNo(AccNo);// "6222121212120011");//付款人银行卡号，通过接口获取
		e4028ReqDTO.setStatus("0");
		e4028ReqDTO.setPageNo("1");
		String xmlBody = JaxbUtil.convertToXml(e4028ReqDTO);
		String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
		System.out.println("sendData=[" + packets + "]");

		Packets packetsRP = EbankUtil.send2server(serverIp, iPort, packets, inte);

		byte[] headRP = packetsRP.getHead();// 报文头
		int bodyRpLen = packetsRP.getLen();// 报文体长度
		byte[] bodyRP = packetsRP.getBody();// 报文体

		StringBuilder rcvMsg = new StringBuilder();
		String recvBody = null;
		if (headRP != null) {
			rcvMsg.append(new String(headRP, EbankUtil.CHARSET));
		}
		if (bodyRpLen > 0 && bodyRP != null) {
			recvBody = new String(bodyRP, EbankUtil.CHARSET);
			rcvMsg.append(new String(bodyRP, EbankUtil.CHARSET));
		}
		// 获取应答信息
		byte[] res = new byte[100];

		System.out.println("respCode=[" + rcvMsg.toString().substring(87, 93) + "]");
		System.arraycopy(rcvMsg.toString().getBytes(), 93, res, 0, 100);
		System.out.println("respMsg=[" + new String(res) + "]");
		System.out.println("recvData=[" + rcvMsg.toString() + "]");

		E4028ResultDTO e4028ResultDTO = new E4028ResultDTO();
		e4028ResultDTO.setRespCode(rcvMsg.toString().substring(87, 93).trim());
		e4028ResultDTO.setRespMsg(new String(res).trim());
		// 判断：如果状态正常(000000)，则返回可发起橙e收款交易
		if ("000000".equals(rcvMsg.toString().substring(87, 93))) {
			e4028ResultDTO.setSuccess(true);
			E4028RespDTO e4028RespDTO = JaxbUtil.converyToJavaBean(recvBody, E4028RespDTO.class);
			e4028ResultDTO.setE4028RespDTO(e4028RespDTO);
			for (E4028RespBodyDTO e4028RespBodyDTO : e4028RespDTO.getList()) {
				System.out.println(e4028RespBodyDTO.toString());
			}
		}

		return e4028ResultDTO;
	}

	/**
	 * 付款人信息维护(查询--》返回付款人信息不存在时调用新增，当付款人信息有变动时调用修改/删除付款人信息)
	 * 
	 * @param AccNo
	 * @return
	 */
	public ResultDTO E4029Trade(E4029Entity e4029Entity) {

		if (E4029Maintain(e4029Entity)) {
			return ResultDTO.success();
		} else {
			return ResultDTO.fail();
		}
	}

	public boolean E4029Maintain(E4029Entity e4029Entity) {
		// 付款人协议查询
		try {
			E4028ResultDTO e4028ResultDTO = E4028Query(e4029Entity.getOppAccNo());
			// 查询成功说明协议已经建立，此时不需要进行新增处理
			if (e4028ResultDTO.isSuccess()) {

				// 如果不是新增操作,则直接进行修改或者删除
				if (!"1".equals(e4029Entity.getTranFlag())) {
					Integer inte = 0;
					String bsnCode = "4029";// 交易码

					E4029ReqDTO e4029ReqDTO = new E4029ReqDTO();
					e4029ReqDTO.setSrcAccNo(SrcAccNo);// 企业账号 ---后续配置
					e4029ReqDTO.setSrcAccName(SrcAccName);// 收款人户名 ---后续配置
					e4029ReqDTO.setAGREE_NO(AGREE_NO);// 协议号 ---后续配置
					e4029ReqDTO.setBusiType(BusiType);// 费项代码
					e4029ReqDTO.setTranFlag(e4029Entity.getTranFlag());// 操作标志:1-新增；2-修改；3-删除

					// 五要素：账号、户名、证件类型、证件号码、开户预留手机号
					e4029ReqDTO.setOppAccNo(e4029Entity.getOppAccNo());// ("6222121212120011"付款人帐号
					e4029ReqDTO.setOppAccName(e4029Entity.getOppAccName());// "张三"
																			// 付款人户名
					e4029ReqDTO.setOppBank(e4029Entity.getOppBank());// "10002"
																		// 付款人银行
					e4029ReqDTO.setMobile(e4029Entity.getMobile());// "15555555555"
																	// 付款人手机号
					e4029ReqDTO.setCardAcctFlag(e4029Entity.getCardAcctFlag());// "0"
																				// 卡折标志:0-借记卡
					e4029ReqDTO.setIdType(e4029Entity.getIdType());// "1"
																	// 证件类型，1-身份证；2-军官证
					e4029ReqDTO.setIdNo(e4029Entity.getIdNo());// "510703199310052817"
																// 证件号码

					e4029ReqDTO.setDetailNo(e4028ResultDTO.getE4028RespDTO().getList().get(0).getDetailNo());// 明细序号,需要查询返回的结果

					String xmlBody = JaxbUtil.convertToXml(e4029ReqDTO);
					String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
					System.out.println("sendData=[" + packets + "]");

					Packets packetsRP = EbankUtil.send2server(serverIp, iPort, packets, inte);

					byte[] headRP = packetsRP.getHead();
					int bodyRpLen = packetsRP.getLen();
					byte[] bodyRP = packetsRP.getBody();

					StringBuilder rcvMsg = new StringBuilder();
					if (headRP != null) {
						rcvMsg.append(new String(headRP, EbankUtil.CHARSET));
					}
					if (bodyRpLen > 0 && bodyRP != null) {
						rcvMsg.append(new String(bodyRP, EbankUtil.CHARSET));
					}
					// 获取应答信息
					byte[] res = new byte[100];
					System.out.println("respCode=[" + rcvMsg.toString().substring(87, 93) + "]");
					System.arraycopy(rcvMsg.toString().getBytes(), 93, res, 0, 100);
					System.out.println("respMsg=[" + new String(res) + "]");

					// 判断：如果维护返回正常(000000)，则返回成功true
					if ("000000".equals(rcvMsg.toString().substring(87, 93))) {
						return true;
					}
				}
			} else {

			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * 付款人信息认证(查询-->返回数据成功<有该协议>，但是付款人状态为待认证/停用时，发起付款方信息认证)
	 * 
	 * @param acctNo:付款人帐号
	 * @param mobile：付款人帐号对于银行预留手机号
	 * @return
	 */
	public ResultDTO E4031Trade(String acctNo, String mobile) {

		E4028ResultDTO e4028ResultDTO;
		try {
			e4028ResultDTO = E4028Query(acctNo);
			// 认证功能要求必须付款人先建立有协议，但是协议状态为1-停用或者为2-待认证，才去发起认证
			if (e4028ResultDTO.isSuccess()) {
				Integer inte = 0;
				String bsnCode = "4031";// 交易码

				E4031DTO e4031dto = new E4031DTO();
				e4031dto.setSrcAccNo(SrcAccNo);// 企业账号 ---后续配置
				e4031dto.setAGREE_NO(AGREE_NO);// 协议号 ---后续配置
				e4031dto.setBusiType(BusiType);// 费项代码
				e4031dto.setOppAccNo(acctNo);// 付款人帐号
				e4031dto.setMobile(mobile);// 付款人手机号

				String xmlBody = JaxbUtil.convertToXml(e4031dto);
				String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
				System.out.println("sendData=[" + packets + "]");
				String serverIp = "localhost";
				Integer iPort = 7072;

				Packets packetsRP = EbankUtil.send2server(serverIp, iPort, packets, inte);

				byte[] headRP = packetsRP.getHead();
				int bodyRpLen = packetsRP.getLen();
				byte[] bodyRP = packetsRP.getBody();

				StringBuilder rcvMsg = new StringBuilder();
				if (headRP != null) {
					rcvMsg.append(new String(headRP, EbankUtil.CHARSET));
				}
				if (bodyRpLen > 0 && bodyRP != null) {
					rcvMsg.append(new String(bodyRP, EbankUtil.CHARSET));
				}
				// 获取应答信息
				byte[] res = new byte[100];
				System.out.println("respCode=[" + rcvMsg.toString().substring(87, 93) + "]");
				System.arraycopy(rcvMsg.toString().getBytes(), 93, res, 0, 100);
				System.out.println("respMsg=[" + new String(res) + "]");
				System.out.println("recvData=" + rcvMsg.toString());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ResultDTO.fail();
	}

	/**
	 * 付款
	 * 
	 * @param AccNo
	 * @param amount
	 * @return
	 */
	public ResultDTO<E4032Entity> E4032Trade(String AccNo, BigDecimal amount) {

		E4028ResultDTO e4028ResultDTO;
		E4032Entity e4032Entity = new E4032Entity();
		try {
			e4028ResultDTO = E4028Query(AccNo);// 先查询付款人协议
			if (e4028ResultDTO.isSuccess()) {

				E4028RespBodyDTO e4028RespBodyDTO = e4028ResultDTO.getE4028RespDTO().getList().get(0);

				// 状态正常，才能进行代扣
				if ("0".equals(e4028RespBodyDTO.getStatus())) {
					Integer inte = 0;
					String bsnCode = "4032";// 交易码

					E4032ReqDTO e4032ReqDTO = new E4032ReqDTO();
					e4032ReqDTO.setThirdVoucher(CodeUtil.generateBatchCode("PA"));// 批次凭证号，唯一凭证号
					e4032ReqDTO.setAGREE_NO(AGREE_NO);// 协议号 ---后续配置
					e4032ReqDTO.setBusiType(BusiType);// 费项代码
					e4032ReqDTO.setCurrency("RMB");// 币种
					e4032ReqDTO.setSrcAccNo(SrcAccNo);// 企业账号 ---后续配置
					e4032ReqDTO.setTotalNum("1");// 总比数
					e4032ReqDTO.setTotalAmount(amount);// 总金额
					e4032ReqDTO.setSettleType("1");// 实时入账标志

					E4032ReqBodyDTO e4032ReqBodyDTO = new E4032ReqBodyDTO();
					e4032ReqBodyDTO.setSThirdVoucher(e4032ReqDTO.getThirdVoucher() + "ST01");// 单笔凭证号
					e4032ReqBodyDTO.setCstInnerFlowNo(
							StringUtil.formatStrFixLenLeft(CodeUtil.generateOrderCode("e"), 20, " "));// 自定义流水号,此字段为订单号，需保持唯一
					e4032ReqBodyDTO.setOppAccNo(e4028RespBodyDTO.getOppAccNo());// 付款人帐号
					e4032ReqBodyDTO.setOppAccName(e4028RespBodyDTO.getOppAccName());// 付款人姓名
					e4032ReqBodyDTO.setOppBank(e4028RespBodyDTO.getOppBank());// 付款人银行
					e4032ReqBodyDTO.setCardAcctFlag(e4028RespBodyDTO.getCardAcctFlag());// 卡折标志
					e4032ReqBodyDTO.setAmount(amount);// 交易金额
					e4032ReqBodyDTO.setIdType(e4028RespBodyDTO.getIdType());// 证件类型
					e4032ReqBodyDTO.setIdNo(e4028RespBodyDTO.getIdNo());// 证件号码
					e4032ReqBodyDTO.setMobile(e4028RespBodyDTO.getMobile());// 手机号
					e4032ReqBodyDTO.setPostScript("");// 附言，备注
					e4032ReqBodyDTO.setMerchantName(SrcAccName);// 商户名称
					List<E4032ReqBodyDTO> list = new ArrayList<>();
					list.add(e4032ReqBodyDTO);
					e4032ReqDTO.setList(list);

					String xmlBody = JaxbUtil.convertToXml(e4032ReqDTO);
					String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
					System.out.println("sendData=[" + packets + "]");
					String serverIp = "localhost";
					Integer iPort = 7072;

					Packets packetsRP = EbankUtil.send2server(serverIp, iPort, packets, inte);

					byte[] headRP = packetsRP.getHead();
					int bodyRpLen = packetsRP.getLen();
					byte[] bodyRP = packetsRP.getBody();

					StringBuilder rcvMsg = new StringBuilder();
					if (headRP != null) {
						rcvMsg.append(new String(headRP, EbankUtil.CHARSET));
					}
					if (bodyRpLen > 0 && bodyRP != null) {
						rcvMsg.append(new String(bodyRP, EbankUtil.CHARSET));
					}
					// 获取应答信息
					byte[] res = new byte[100];
					String respCode = rcvMsg.toString().substring(87, 93);
					System.out.println("respCode=[" + rcvMsg.toString().substring(87, 93) + "]");
					System.arraycopy(rcvMsg.toString().getBytes(), 93, res, 0, 100);
					System.out.println("respMsg=[" + new String(res) + "]");
					System.out.println("recvData=" + rcvMsg.toString());

					if ("YQ9999".equals(respCode) || "GW3002".equals(respCode) || "EBLN00".equals(respCode)
							|| "AFE003".equals(respCode) || "AFE004".equals(respCode) || "E00006".equals(respCode)
							|| "E00007".equals(respCode) || "E00008".equals(respCode) || "YQ9989".equals(respCode)
							|| "YQ9976".equals(respCode)) {

						e4032Entity.setRespCode("1000");
						e4032Entity.setRespMsg("交易进行中，请稍候查询结果");
						e4032Entity.setOrderNo(e4032ReqDTO.getThirdVoucher());// 批次凭证号
						return ResultDTO.fail(e4032Entity);
					} else if ("000000".equals(respCode)) {
						e4032Entity.setRespCode("1001");
						e4032Entity.setRespMsg("交易成功");
						e4032Entity.setOrderNo(e4032ReqDTO.getThirdVoucher());// 批次凭证号
						return ResultDTO.success(e4032Entity);
					} else {
						return ResultDTO.fail("交易失败");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResultDTO.fail(E789ApiConstant.E_AGREEE_NOT_FOUND);
	}

	/**
	 * 付款交易结果查询主体方法
	 * 
	 * @param orderNo：订单号
	 * @return
	 */
	public E4033ResultDTO E4033ResultQuery(String orderNo) {

		try {
			Integer inte = 0;
			// 需要调用E4033查询交易结果
			E4032ReqDTO e4032ReqDTO = new E4032ReqDTO();
			e4032ReqDTO.setThirdVoucher(orderNo);// 批次凭证号
			String bsnCode = "4033";// 交易码
			String xmlBody = JaxbUtil.convertToXml(e4032ReqDTO);
			String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
			System.out.println("sendData=[" + packets + "]");

			Packets packetsRP;

			packetsRP = EbankUtil.send2server(serverIp, iPort, packets, inte);

			byte[] headRP = packetsRP.getHead();
			int bodyRpLen = packetsRP.getLen();
			byte[] bodyRP = packetsRP.getBody();
			String recvBody = null;

			StringBuilder rcvMsg = new StringBuilder();
			if (headRP != null) {
				rcvMsg.append(new String(headRP, EbankUtil.CHARSET));
			}
			if (bodyRpLen > 0 && bodyRP != null) {
				recvBody = new String(bodyRP, EbankUtil.CHARSET);
				rcvMsg.append(new String(bodyRP, EbankUtil.CHARSET));
			}
			// 获取应答信息
			byte[] res = new byte[100];
			String respCode = rcvMsg.toString().substring(87, 93);
			System.out.println("respCode=[" + rcvMsg.toString().substring(87, 93) + "]");
			System.arraycopy(rcvMsg.toString().getBytes(), 93, res, 0, 100);
			System.out.println("respMsg=[" + new String(res) + "]");
			System.out.println("recvData=" + rcvMsg.toString());

			E4033ResultDTO e4033ResultDTO = new E4033ResultDTO();
			e4033ResultDTO.setRespCode(respCode);
			e4033ResultDTO.setRespMsg(new String(res).trim());

			if ("000000".equals(rcvMsg.toString().substring(87, 93))) {

				e4033ResultDTO.setSuccess(true);
				e4033ResultDTO = JaxbUtil.converyToJavaBean(recvBody, E4033ResultDTO.class);
				System.out.println(e4033ResultDTO.toString());
			}

			return e4033ResultDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 付款交易结果查询
	 * 
	 * @param orderNo：订单号
	 * @return
	 */
	public ResultDTO E4033Trade(String orderNo) {

		E4033ResultDTO e4033ResultDTO = E4033ResultQuery(orderNo);

		if (e4033ResultDTO.isSuccess()) {
			return ResultDTO.success();
		}
		return ResultDTO.fail();
	}
}
