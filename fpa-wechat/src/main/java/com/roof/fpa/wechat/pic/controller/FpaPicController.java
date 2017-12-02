package com.roof.fpa.wechat.pic.controller;

import com.roof.fpa.core.file.api.IFpaPicService;
import org.roof.fileupload.api.FileInfo;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhenglt on 2017/11/29.
 */
@Controller
@RequestMapping("/fpa/pic")
public class FpaPicController {

    private Logger logger = LoggerFactory.getLogger(FpaPicController.class);


    @Autowired
    private IFpaPicService fpaPicService;

    @RequestMapping(value = "upload", method = {RequestMethod.POST})
    @ResponseBody
    public Result uploadFile(@RequestParam("upfile") MultipartFile file, HttpServletRequest request) {
        try {
            FileInfo fileinfo = fpaPicService.saveFile(file);
            return new Result(Result.SUCCESS, "", fileinfo.getName());
        } catch (Exception e) {
            return new Result(Result.FAIL, "上传失败");
        }
    }

    private void flush(InputStream in, HttpServletResponse response) {
        if (in == null) {
            logger.error("流为空");
            return;
        }
        response.setContentType("image/png");
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

    private String getPicType(HttpServletRequest request) {
        String s = "";
        String uri = request.getRequestURI();
        int i = uri.lastIndexOf(".");
        if (i != -1) {
            s = uri.substring(i);
        }
        return s;
    }

    @RequestMapping(value = "/big/{filename}", method = {RequestMethod.GET})
    public void getBigFile(@PathVariable String filename, HttpServletResponse response, HttpServletRequest request) {
        InputStream in = fpaPicService.getBigFile(filename + getPicType(request));
        flush(in, response);
    }

    @RequestMapping(value = "/middle/{filename}", method = {RequestMethod.GET})
    public void getMiddleFile(@PathVariable String filename, HttpServletResponse response, HttpServletRequest request) {

        InputStream in = fpaPicService.getMiddleFile(filename + getPicType(request));
        flush(in, response);
    }

    @RequestMapping(value = "/small/{filename}", method = {RequestMethod.GET})
    public void getSmallFile(@PathVariable String filename, HttpServletResponse response, HttpServletRequest request) {
        InputStream in = fpaPicService.getSmallFile(filename + getPicType(request));
        flush(in, response);
    }


}
