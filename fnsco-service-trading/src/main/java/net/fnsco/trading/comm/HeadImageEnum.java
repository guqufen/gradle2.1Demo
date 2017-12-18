package net.fnsco.trading.comm;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import net.fnsco.core.constants.CoreConstants;

public class HeadImageEnum extends CoreConstants{
    public enum HeadImage {
        //注册默认头像
        NUM1("00", "e789-test^2017/12/1513564502588.jpg"), 
        NUM2("01", "e789-test^2017/12/1513564502588.jpg"),
    	NUM3("02", "e789-test^2017/12/1513564502588.jpg"), 
        NUM4("03", "e789-test^2017/12/1513564502588.jpg"),
        NUM5("04", "e789-test^2017/12/1513564502588.jpg"), 
        NUM6("05", "e789-test^2017/12/1513564502588.jpg"),
        NUM7("06", "e789-test^2017/12/1513564502588.jpg"),
        NUM8("07", "e789-test^2017/12/1513564502588.jpg"),
        NUM9("08", "e789-test^2017/12/1513564502588.jpg"); 
        private String code;
        private String name;

        HeadImage(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new TreeMap<>();
            for (HeadImage mType : HeadImage.values()) {
                map.put(mType.code, mType.name);
            }
            return map;
        }

        public static String getHeadImage(String code) {
            for (HeadImage eType : HeadImage.values()) {
                if (eType.code.equals(code)) {
                    return eType.name;
                }
            }
            return "";
        }
    }
    
    public static String getImage() {
    	Integer num = new Random().nextInt(9);
    	switch (num) {
		case 0: return HeadImage.getHeadImage("00");
		case 1: return HeadImage.getHeadImage("01");
		case 2: return HeadImage.getHeadImage("02");
		case 3: return HeadImage.getHeadImage("03");
		case 4: return HeadImage.getHeadImage("04");
		case 5: return HeadImage.getHeadImage("05");
		case 6: return HeadImage.getHeadImage("06");
		case 7: return HeadImage.getHeadImage("07");
		case 8: return HeadImage.getHeadImage("08");
		default:
			break;
		}
		return null;
    }
}
