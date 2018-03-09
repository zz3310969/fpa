package ant.test;

import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.service.api.IAreaService;
import com.roof.advisory.cos.service.api.ICosService;
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
public class CosServiceImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ICosService cosService;

    @org.junit.Test
    public void signTest() throws Exception {
        cosService.getSign();
    }

}