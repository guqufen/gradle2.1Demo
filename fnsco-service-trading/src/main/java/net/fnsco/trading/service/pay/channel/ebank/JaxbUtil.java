package net.fnsco.trading.service.pay.channel.ebank;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.poi.ss.formula.functions.T;

/**
 * Jaxb2工具类,用于xml和JavaBean的相互转化
 * 
 * @author 伯约
 *
 */
@XmlRootElement
public class JaxbUtil {

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 注意jdk版本
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(baos,
					(String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
			xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");

			marshaller.marshal(obj, xmlStreamWriter);
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.close();
			return new String(baos.toString("GBK"));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
}
