import com.google.common.collect.Maps;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.fpa.cardtestresult.entity.SimilerResult;
import com.roof.fpa.counselor.service.api.ICounselorService;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by liangl on 2018/1/6.
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class SimilerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IConsultantService consultantService;

    @Autowired
    private IAccountOperateService accountOperateService;

    @org.junit.Test
    public void similerTest() throws Exception {
        int i = "A".compareTo("Y");
        SimilerResult similerResult = customerService.similer(153L,3176L);
    }

    @org.junit.Test
    public void consultantAccount() throws Exception {
        List<ConsultantVo> list = consultantService.selectForList(new Consultant());
        for (Consultant consultant : list){
            accountOperateService.create(consultant.getId(), AccountType.local, Maps.newHashMap());

        }
    }

}