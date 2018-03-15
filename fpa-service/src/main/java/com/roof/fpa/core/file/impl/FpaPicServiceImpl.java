package com.roof.fpa.core.file.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.roof.advisory.cos.service.api.ICosService;
import com.roof.fpa.cache.impl.CacheFactory;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.core.file.api.IFpaPicService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.roof.fileupload.api.FileInfo;
import org.roof.fileupload.api.FileInfoService;
import org.roof.fileupload.api.FileManager;
import org.roof.fileupload.api.FileService;
import org.roof.fileupload.exception.FileInfoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenglt on 2017/10/2.
 */
@Service
public class FpaPicServiceImpl implements IFpaPicService,InitializingBean {

    private Logger logger = LoggerFactory.getLogger(FpaPicServiceImpl.class);

    private FileManager fileManager;

    private FileInfoService fileInfoService;
    private FileService fileService;

    @Autowired
    private ICosService cosService;

    //Cache<Object, Object> cache = CacheFactory.getInstance().getPicCache();
    @Autowired
    private ListeningExecutorService listeningExecutorService;
    private LoadingCache<String, byte[]> cache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, byte[]>() {
                @Override
                public byte[] load(String key) throws Exception {
                    return  getMiddleFileByBase(key);
                }

                @Override
                public ListenableFuture<byte[]> reload(String key, byte[] oldValue) throws Exception {
                    return listeningExecutorService.submit(new Callable<byte[]>() {
                        @Override
                        public byte[] call() throws Exception {
                            return getMiddleFileByBase(key);
                        }
                    });
                }
            });


    @Override
    public void afterPropertiesSet() throws Exception {
        fileInfoService = fileManager.getFileInfoService();
        fileService = fileManager.getFileService();
    }


    public  InputStream getBigFile(String filename){
        InputStream in = fileManager.getFile(filename);
        return in;
    }

    public InputStream getSmallFile(String filename){
        FileInfo fileInfo = null;
        try {
            fileInfo = fileInfoService.loadByName(filename);
        } catch (FileInfoNotFoundException e) {
            return null;
        }
        String minPath = toSmallPath(fileInfo.getRealPath());
        Path path = Paths.get(minPath);

        if(!Files.exists(path)){
            try {
                smallImage(fileInfo.getRealPath(),minPath);
            } catch (IOException e) {
                logger.error("生成缩略图出错:",e);
                return null;
            }
        }
        fileInfo.setRealPath(minPath);
        //getImageInCache(filename,fileInfo);//
        byte[] bs = fileService.loadDataByFileInfo(fileInfo);
        return new ByteArrayInputStream(bs);
    }

    public FileInfo saveFile(MultipartFile file) throws Exception {
        Map<String, Object> xdata = new HashMap<String, Object>();
        xdata.put("displayName", file.getOriginalFilename());
        xdata.put("fileSize", file.getSize());
        xdata.put("ContentType", file.getContentType());

        ByteArrayInputStream in = new ByteArrayInputStream(file.getBytes());
        FileInfo fileinfo = fileManager.saveFile(in, xdata);
        //this.middleImage(fileinfo.getRealPath(),toMiddlePath(fileinfo.getRealPath()));
        this.smallImage(fileinfo.getRealPath(),toSmallPath(fileinfo.getRealPath()));
        return fileinfo;
    }

    public String uploadCos(FileInfo fileinfo){
        String path =  cosService.uploadHeadImg(fileinfo.getName(),new File(toSmallPath(fileinfo.getRealPath())));
        return path;
    }

    public  byte[] getMiddleFileByBase(String filename){
        FileInfo fileInfo = null;
        try {
            fileInfo = fileInfoService.loadByName(filename);
        } catch (FileInfoNotFoundException e) {
            return null;
        }
        String smallPath = toMiddlePath(fileInfo.getRealPath());
        Path path = Paths.get(smallPath);

        if(!Files.exists(path)){
            try {
                middleImage(fileInfo.getRealPath(),smallPath);
            } catch (IOException e) {
                logger.error("生成缩略图出错:",e);
                return null;
            }
        }
        fileInfo.setRealPath(smallPath);

        byte[] bs = fileService.loadDataByFileInfo(fileInfo);
        return bs;
    }

    public  InputStream getMiddleFile(String filename){
        /*FileInfo fileInfo = null;
        try {
            fileInfo = fileInfoService.loadByName(filename);
        } catch (FileInfoNotFoundException e) {
            return null;
        }
        String smallPath = toMiddlePath(fileInfo.getRealPath());
        Path path = Paths.get(smallPath);

        if(!Files.exists(path)){
            try {
                middleImage(fileInfo.getRealPath(),smallPath);
            } catch (IOException e) {
                logger.error("生成缩略图出错:",e);
                return null;
            }
        }
        fileInfo.setRealPath(smallPath);
*/
        //byte[] bs = fileService.loadDataByFileInfo(fileInfo);
        byte[] bs = null;
        try {
            bs = cache.get(filename);
            return new ByteArrayInputStream(bs);
        } catch (ExecutionException e) {
            logger.error(e.getMessage(),e);
        }
        bs = getMiddleFileByBase(filename);
        return new ByteArrayInputStream(bs);

    }

    public void middleImage(String filePath,String toPath) throws IOException {
        Thumbnails.of(filePath)
                .scale(1f)
                .outputQuality(0.5f)
                .toFile(toPath);

    }


    /*private byte[] getImageInCache(String imaegName, FileInfo fileInfo){
        Assert.notNull(fileInfo,"fileInfo 对象不能为空");
        Assert.notNull(fileInfo.getRealPath(),"路径不能为空");
        Assert.notNull(imaegName,"图片名称不能为空");
        try {
             return (byte[])cache.get(imaegName, new Callable<byte[]>() {
                @Override
                public byte[] call() throws Exception {
                    return fileService.loadDataByFileInfo(fileInfo);
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    public void smallImage(String filePath,String toPath) throws IOException {
        Thumbnails.of(filePath)
                .scale(0.25)
                .outputQuality(0.5f)
                .toFile(toPath);

    }


    private String toMiddlePath(String str){
        String small =  StringUtils.replace(str,".","-middle.");
        return small;
    }

    private String toSmallPath(String str){
        String small =  StringUtils.replace(str,".","-small.");
        return small;
    }


    @Autowired(required = true)
    public void setFileManager(@Qualifier("simpleFileManager") FileManager fileManager) {
        this.fileManager = fileManager;
    }


}
