import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;

/**
 * Created by liangl on 2018/1/6.
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class WeChatHanderImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IWeChatHander weChatHander;

    @org.junit.Test
    public void getacode() throws Exception {
        weChatHander.getacode();
    }

}