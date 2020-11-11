package com.locus.roleSystem.service;

import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.response.BaseResponse;

public interface ExceptionHelperService {
    BaseResponse getBaseResponseFromBaseException(BaseException exception);

    BaseResponse getBaseResponseFromException(Exception exception);
}
