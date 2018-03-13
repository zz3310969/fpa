package ant.test;

import com.roof.advisory.cos.service.api.ICosService;
import com.roof.advisory.im.service.impl.ImService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liangl on 2018/1/6.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class ImServiceImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ImService imService;

    @org.junit.Test
    public void openSessionTest() throws Exception {
        //imService.openSession();
    }

}