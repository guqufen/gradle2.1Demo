package net.fnsco.trading.comm;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import net.fnsco.core.constants.CoreConstants;

public class HeadImageEnum extends CoreConstants{
    public enum HeadImage {
        //注册默认头像
        NUM1("00", "/app/headimage/1.png"), 
        NUM2("01", "/app/headimage/2.png"),
    	NUM3("02", "/app/headimage/3.png"), 
        NUM4("03", "/app/headimage/4.png"),
        NUM5("04", "/app/headimage/5.png"), 
        NUM6("05", "/app/headimage/6.png"),
        NUM7("06", "/app/headimage/7.png"),
        NUM8("07", "/app/headimage/8.png"),
        NUM9("08", "/app/headimage/9.png"); 
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
