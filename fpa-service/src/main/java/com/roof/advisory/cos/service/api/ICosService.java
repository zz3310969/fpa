package com.roof.advisory.cos.service.api;

import java.util.Map;

public interface ICosService {

    Map<String, Object> getSign(Map<String,String> params);

    Map<String,Object> getConfiguration();

}
