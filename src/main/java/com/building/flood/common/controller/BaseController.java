package com.building.flood.common.controller;

import com.building.flood.common.Response.CommonResult;
import com.building.flood.common.Response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BaseController {
    @Autowired
    private ResponseService responseService;

    public CommonResult success()
    {
        return responseService.getSuccessResult();
    }
    public CommonResult result(Object data)
    {
        return responseService.getSingleResult(data);
    }


}
