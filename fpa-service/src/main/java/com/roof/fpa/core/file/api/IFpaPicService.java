package com.roof.fpa.core.file.api;

import org.roof.fileupload.api.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by zhenglt on 2017/11/29.
 */
public interface IFpaPicService {

    FileInfo saveFile(MultipartFile file) throws Exception;

    public InputStream getSmallFile(String filename);

    public InputStream getMiddleFile(String filename);

    public InputStream getBigFile(String filename);


}
