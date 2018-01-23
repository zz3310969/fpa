package ant.test;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by zhenglt on 2018/1/9.
 */
public class AntTest {

    protected static PathMatcher pathMatcher = new AntPathMatcher();

    public static void main(String[] s){
        if(pathMatcher.match("* /**/fpa/wechat*/*", "get /fpa/wechat/scene/1")) {
            System.out.println("匹配成功");
        }else {
            System.out.println("匹配失败");
        }
    }

}
