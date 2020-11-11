package com.locus.roleSystem.service.impl;


import com.locus.roleSystem.constant.Status;
import com.locus.roleSystem.exception.BaseException;
import com.locus.roleSystem.response.BaseResponse;
import com.locus.roleSystem.service.ExceptionHelperService;
import org.springframework.stereotype.Service;

@Service("ExceptionHelperServiceImpl")
public class ExceptionHelperServiceImpl implements ExceptionHelperService {

    @Override
    public BaseResponse getBaseResponseFromBaseException(BaseException exception) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        baseResponse.setReason(exception.getMessage());

        return baseResponse;
    }

    @Override
    public BaseResponse getBaseResponseFromException(Exception exception) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        baseResponse.setReason(exception.getMessage());
        return baseResponse;
    }
}
