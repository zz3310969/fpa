package ant.test;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenglt on 2018/1/9.
 */
public class AntTest {

    protected static PathMatcher pathMatcher = new AntPathMatcher();

    public static void main1(String[] s){
        if(pathMatcher.match("* /**/fpa/wechat*/*", "get /fpa/wechat/scene/1")) {
            System.out.println("匹配成功");
        }else {
            System.out.println("匹配失败");
        }
    }

    public static void main(String[] s) {
        Pattern p = Pattern.compile("PUB-AREA-.*-25");
        Matcher m = p.matcher("PUB-AREA-1-310000-25");
        if (m.matches()) { // 返回true
            System.out.println("true");
        }
    }

}
