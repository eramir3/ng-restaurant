package com.restaurant.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.dto.response.ErrorResponseContainer;
import com.restaurant.dto.response.ResponseContainer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
        filterChain.doFilter(servletRequest, responseWrapper);

        String dataContent = new String(responseWrapper.getDataStream());
        int status = httpServletResponse.getStatus();
        String message = HttpStatus.valueOf(status).getReasonPhrase();

        ObjectMapper mapper = new ObjectMapper();
        Object data = mapper.readValue(dataContent, Object.class);

        byte[] responseToSend;

        if (HttpStatus.valueOf(status).is2xxSuccessful()) {
            ResponseContainer response = new ResponseContainer(data, message, status);
            responseToSend = restResponseBytes(response);
        } else {
            ErrorResponseContainer errorResponse = new ErrorResponseContainer(data, message, status);
            responseToSend = restResponseBytes(errorResponse);
        }

        servletResponse.getOutputStream().write(responseToSend);
    }

    private byte[] restResponseBytes(Object response) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(response);
        return serialized.getBytes();
    }
}
