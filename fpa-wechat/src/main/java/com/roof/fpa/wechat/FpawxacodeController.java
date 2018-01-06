package com.roof.fpa.wechat;

import com.alibaba.fastjson.JSONObject;
import com.roof.fpa.core.http.HttpClientUtil;
import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.roof.commons.PropertiesUtil;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fpa/wxacode")
public class FpawxacodeController {
    private Logger logger = LoggerFactory.getLogger(FpawxacodeController.class);

    @Autowired
    private IWeChatHander weChatHander;

    private String wxacodeunlimit = PropertiesUtil.getPorpertyString("fpa.mini.getwxacodeunlimit");

    private String wxacodeFilePath = PropertiesUtil.getPorpertyString("fpa.mini.wxacodeFilePath");


    @RequestMapping(value = "/getacode", method = {RequestMethod.GET})
    public void getMiddleFile(Long userId,HttpServletResponse response, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
//        User user = (User) BaseUserContext.getCurrentUser(request);
        Map<String,Object> userParam = new HashMap<String,Object>();
        userParam.put("userId",userId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scene", JSONObject.toJSONString(userParam));
        map.put("page", "pages/index/index");

        try {
            String savePath = wxacodeFilePath + "acode-userid-" + userId + ".jpeg";
            String filePath = HttpClientUtil.postFile(wxacodeunlimit + "?access_token=" + weChatHander.getAccess_token(), JSONObject.toJSONString(map), savePath);
            if (savePath.equals(filePath)) {
                flush(new FileInputStream(filePath), response);
            }
            //            HttpEntity<InputStream> restre = restTemplate.exchange(
//                    wxacodeunlimit + "?access_token=" + weChatHander.getAccess_token(), HttpMethod.POST, entity,InputStream.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void flush(InputStream in, HttpServletResponse response) {
        if (in == null) {
            logger.error("流为空");
        }
        response.setContentType("image/jpeg");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            byte[] buf = new byte[1024];
            while (in.read(buf) != -1) {
                out.write(buf);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("关闭图片流失败:", e.getCause());
            }
        }
    }
}
