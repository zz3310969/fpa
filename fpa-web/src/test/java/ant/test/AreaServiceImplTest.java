package ant.test;

import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.service.api.IAreaService;
import com.roof.fpa.weixin.service.api.IWeChatHander;
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
public class AreaServiceImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IAreaService areaService;

    @org.junit.Test
    public void treeTest() throws Exception {
        areaService.tree(new Area());
    }

}