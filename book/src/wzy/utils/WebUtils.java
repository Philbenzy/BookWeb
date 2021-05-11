package wzy.utils;
import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(Map value, T Bean){
        try {
            System.out.println("注入之前："+Bean);
            BeanUtils.populate(Bean,value);
            System.out.println("注入之后："+Bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Bean;
    }

    public static int parseInt(String strInt, int defaultValue){
        try{
            return Integer.parseInt(strInt);
        }catch (Exception e){
            //e.printStackTrace();
        }
        return defaultValue;
    }

}
