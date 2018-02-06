import com.roof.fpa.cardtestresult.entity.SimilerResult;
import com.roof.fpa.counselor.service.api.ICounselorService;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liangl on 2018/1/6.
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class SimilerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ICustomerService customerService;

    @org.junit.Test
    public void similerTest() throws Exception {
        int i = "A".compareTo("Y");
        SimilerResult similerResult = customerService.similer(153L,3176L);
    }

}