import com.alibaba.fastjson.JSON;
import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.InetAddress;
import java.util.SortedMap;

/**
 * Created by liangl on 2018/1/6.
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@WebAppConfiguration
public class WeChatHanderImplTest extends AbstractJUnit4SpringContextTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatHanderImplTest.class);

    @Autowired
    private IWeChatHander weChatHander;

    @org.junit.Test
    public void getacode() throws Exception {
        weChatHander.getacode();
    }

    protected Chain chatOrderCreateChain;

    @Test
    public void dochain() {
        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        advisoryOrderVo.setConsId(1L);//咨询师
        advisoryOrderVo.setCustomId(152L);//
        advisoryOrderVo.setProductId(1L);

        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            advisoryOrderVo.setIp(addr);
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            LOGGER.info(JSON.toJSONString(packageP));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Test
    public void extendOrderChain() {
        AdvisoryOrderVo advisoryOrderVo = new AdvisoryOrderVo();
        advisoryOrderVo.setConsId(1L);//咨询师
        advisoryOrderVo.setCustomId(152L);//
        advisoryOrderVo.setProductId(1L);

        ValueStack valueStack = new GenericValueStack();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            valueStack.set("ip", addr);
            valueStack.set("hasLink", "extend");
            valueStack.set("advisoryOrderVo", advisoryOrderVo);
            chatOrderCreateChain.doChain(valueStack);

            SortedMap<Object, Object> packageP = (SortedMap<Object, Object>) valueStack.get("packageP");
            LOGGER.info(JSON.toJSONString(packageP));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Autowired
    public void setChatOrderCreateChain(@Qualifier("chatOrderCreateChain") Chain chatOrderCreateChain) {
        this.chatOrderCreateChain = chatOrderCreateChain;
    }
}