package ant.test;

import com.roof.fpa.core.http.HttpClientUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenglt on 2018/1/9.
 */
public class HttpTest {




    public static void main(String[] s) {
        System.out.println(HttpClientUtil.get("https://www.baidu.com/"));
        System.out.println(HttpClientUtil.get("http://www.qq.com/"));;
    }

}
