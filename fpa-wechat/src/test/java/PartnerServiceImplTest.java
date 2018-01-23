import com.roof.fpa.partner.service.api.IPartnerService;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liangl on 2018/1/6.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class PartnerServiceImplTest {

    static {
        String javaClassPathProperty = System.getProperty("java.class.path");
        //System.setProperty("java.class.path","");
    }


    @Autowired
    private IPartnerService partnerService;

    @org.junit.Test
    public void bind() throws Exception {
        partnerService.bind("oJKqg0y__7_S2__dAaTZHKLPuY4U",153L);

    }

}