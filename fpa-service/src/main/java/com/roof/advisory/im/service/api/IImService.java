package com.roof.advisory.im.service.api;

import com.roof.advisory.im.service.ImRequest;

public interface IImService {

    void openSession(ImRequest imRequest);
    void closeSession(ImRequest imRequest);

}
