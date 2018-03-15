package ant.test;

import com.roof.advisory.im.service.impl.ImService;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import com.roof.fpa.customer.service.impl.CustomerService;
import org.junit.runner.RunWith;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by liangl on 2018/1/6.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class WxSessionServiceImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IWxSessionService wxSessionService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void createTokenTest() throws Exception {
        //imService.openSession();
        String token = wxSessionService.createToken("om2wi0SSeiULnLSiMPHBCwTtVZL0");
        System.out.println(token);
    }


    @org.junit.Test
    public void reCustomer() throws Exception {
        //imService.openSession();
        CustomerVo customerVo = customerService.loadByOpenid("om2wi0SSeiULnLSiMPHBCwTtVZL0");

        User user = customerService.customerConvertUser(customerVo);
        userService.save(user);
        customerVo.setUserId(user.getId());

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerVo,customer);
        customerService.update(customer);

    }

    @org.junit.Test
    public void reAllCustomer() throws Exception {
        //imService.openSession();
        List<CustomerVo> customerVos = customerService.selectForList(new Customer());
        for (CustomerVo customerVo :customerVos ){
            if(!userService.sameUsername(null,customerVo.getWeixinOpenId())){
                User user = customerService.customerConvertUser(customerVo);
                userService.save(user);
                customerVo.setUserId(user.getId());

                Customer customer = new Customer();
                BeanUtils.copyProperties(customerVo,customer);
                customerService.update(customer);
            }



        }



    }

}