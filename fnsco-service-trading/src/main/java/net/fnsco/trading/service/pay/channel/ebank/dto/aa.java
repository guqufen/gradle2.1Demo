package net.fnsco.trading.service.pay.channel.ebank.dto;

import net.fnsco.trading.service.pay.channel.ebank.EbankUtil;
import net.fnsco.trading.service.pay.channel.ebank.JaxbUtil;
import net.fnsco.trading.service.pay.channel.ebank.Packets;

public class aa {

	public static void main(String[] args) {
		// E4032HeadDTO e4032HeadDTO = new E4032HeadDTO("1", "2", "3", "4",
		// null, 6, new BigDecimal(7), "8");
		// E4032BodyDTO e4032BodyDTO = new E4032BodyDTO();
		// e4032BodyDTO.setSThirdVoucher("9");
		// List<E4032BodyDTO> list = new ArrayList<E4032BodyDTO>();
		// list.add(e4032BodyDTO);
		// e4032HeadDTO.setList(list);
		// String xml = JaxbUtil.convertToXml(e4032HeadDTO);	
		// System.out.println(xml);

		// E4028ReqDTO e4028ReqDTO = new E4028ReqDTO();
		// e4028ReqDTO.setSrcAccNo("0122100613675");
		// e4028ReqDTO.setAGREE_NO("Y000010021");
		// e4028ReqDTO.setBusiType("");
		// e4028ReqDTO.setStartDate("");
		// e4028ReqDTO.setEndDate("");
		// e4028ReqDTO.setOppAccNo("");
		// e4028ReqDTO.setOppAccName("");
		// e4028ReqDTO.setMobile("");
		// e4028ReqDTO.setPageNo("");
		// String xml = JaxbUtil.convertToXml(e4028ReqDTO);
		// System.out.println(xml);

		// String ss = StringUtil.formatFixLenRight("0123456789123456789000",
		// 20, "**");
		// Integer inte = 000;
		// String xx = StringUtil.formatFixLenLeft(inte, 20, "0");
		// System.out.println(CodeUtil.generateOrderCode("E"));

		// try {
		// StringBuffer sBuffer = new StringBuffer();
		// sBuffer.append("A0010101020020108050000801500000000001074028
		// 123450120120620151810201206180000011037
		// 000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		//
		//// EbankDTO ebankDTO = new EbankDTO();
		//// ebankDTO.init("0");
		// E4028ReqDTO e4028ReqDTO = new E4028ReqDTO();
		// e4028ReqDTO.setSrcAccNo("0122100613675");
		// e4028ReqDTO.setAGREE_NO("Y000010021");
		// e4028ReqDTO.setBusiType("");
		// e4028ReqDTO.setStartDate("");
		// e4028ReqDTO.setEndDate("");
		// e4028ReqDTO.setOppAccNo("");
		// e4028ReqDTO.setOppAccName("");
		// e4028ReqDTO.setMobile("");
		// e4028ReqDTO.setPageNo("1");
		// String xml = JaxbUtil.convertToXml(e4028ReqDTO);
		// sBuffer.append(xml);
		// System.out.println(sBuffer.toString());
		// System.out.println(B2BicHttp.doPost("http://localhost:7072",
		// sBuffer.toString()));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		test4028();
	}

	public static void test4028() {
		Integer inte = 0;
		String yqdm = "00901079800000098000";// 外联客户代码
		String bsnCode = "4028";// 交易码
		E4028ReqDTO e4028ReqDTO = new E4028ReqDTO();
		e4028ReqDTO.setSrcAccNo("11014748658003");// 企业账号
		e4028ReqDTO.setAGREE_NO("Y000128002");// 协议号
		e4028ReqDTO.setBusiType("M8PAK");// 费项代码
		// e4028ReqDTO.setOppAccName("刘敏");//付款人户名
//		e4028ReqDTO.setOppAccNo("6222121212120011");
		e4028ReqDTO.setStatus("0");
		e4028ReqDTO.setPageNo("1");
		String xmlBody = JaxbUtil.convertToXml(e4028ReqDTO);
		String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
		System.out.println("sendData=[" + packets + "]");

		String serverIp = "localhost";
		Integer iPort = 7072;
		try {
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
			System.out.println("recvData=[" + new String(bodyRP, EbankUtil.CHARSET) + "]");
			// E4028RespDTO e4028ReqDTO2 = JaxbUtil.converyToJavaBean(new
			// String(bodyRP, EbankUtil.CHARSET),
			// E4028RespDTO.class);
			//
			// System.out.println(e4028ReqDTO2.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test4029() {
		Integer inte = 0;
		String yqdm = "00901079800000098000";// 外联客户代码
		String bsnCode = "4029";// 交易码

		E4029ReqDTO e4029ReqDTO = new E4029ReqDTO();
		e4029ReqDTO.setSrcAccNo("11014748658003");// 企业账号
		e4029ReqDTO.setSrcAccName("SHENFA014004877");// 收款人户名
		e4029ReqDTO.setAGREE_NO("Y000128002");// 协议号
		e4029ReqDTO.setBusiType("M8PAK");// 费项代码
		e4029ReqDTO.setTranFlag("2");// 操作标志:1-新增；2-修改；3-删除

		e4029ReqDTO.setOppAccNo("6222121212120011");// 付款人帐号
		e4029ReqDTO.setOppAccName("张三");// 付款人户名
		e4029ReqDTO.setOppBank("10002");// 付款人银行
		e4029ReqDTO.setMobile("15555555555");// 付款人手机号
		e4029ReqDTO.setCardAcctFlag("0");// 卡折标志:0-借记卡
		e4029ReqDTO.setIdType("1");// 证件类型，1-身份证；2-军官证
		e4029ReqDTO.setIdNo("510703199310052817");// 证件号码
		e4029ReqDTO.setDetailNo("1000000091");// 明细序号
		String xmlBody = JaxbUtil.convertToXml(e4029ReqDTO);
		String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
		System.out.println("sendData=[" + packets + "]");

		String serverIp = "localhost";
		Integer iPort = 7072;
		try {
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
			// System.out.println("recvData=[" + rcvMsg.toString() + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test4031() {
		Integer inte = 0;
		String yqdm = "00901079800000098000";// 外联客户代码
		String bsnCode = "4031";// 交易码
		E4031DTO e4031dto = new E4031DTO();
		e4031dto.setSrcAccNo("11014748658003");// 企业账号
		e4031dto.setAGREE_NO("Y000128002");// 协议号
		e4031dto.setBusiType("M8PAK");// 费项代码
		e4031dto.setOppAccNo("6222121212120011");
		e4031dto.setMobile("15555555555");

		String xmlBody = JaxbUtil.convertToXml(e4031dto);
		String packets = EbankUtil.asemblyPackets(yqdm, bsnCode, xmlBody, inte);
		System.out.println("sendData=[" + packets + "]");

		String serverIp = "localhost";
		Integer iPort = 7072;
		try {
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
			System.out.println("recvData=[" + rcvMsg.toString() + "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
