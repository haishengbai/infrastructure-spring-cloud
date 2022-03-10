package com.soundai.infrastructure.springcloud.oauth2.server.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundai.infrastructure.springcloud.oauth2.server.model.ResultMsg;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    public static void result(HttpServletResponse response, ResultMsg msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(msg).getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
