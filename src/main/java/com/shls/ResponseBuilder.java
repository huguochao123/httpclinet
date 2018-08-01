package com.shls;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by song on 11/08/2017.
 */
public class ResponseBuilder
{
    public static int SUCCESS = 200;
    public static int ERROR = 500;
    public static int NOT_FOUND = 404;
    public static int UNAUTHORIZED = 401;
    public static int REDIRECT = 302;

    private Map<String, Object> responseMap = new LinkedHashMap<>();

    public ResponseBuilder success()
    {
        code(SUCCESS);
        message("success");
        return this;
    }

    public ResponseBuilder error()
    {
        code(ERROR);
        message("error");
        return this;
    }

    public ResponseBuilder unauthorized()
    {
        code(UNAUTHORIZED);
        message("unauthorized");
        return this;
    }

    public ResponseBuilder code(int code)
    {
        responseMap.put("code", code);
        return this;
    }

    public ResponseBuilder message(String message)
    {
        responseMap.put("message", message);
        return this;
    }

    public ResponseBuilder data(Object data)
    {
        responseMap.put("data", data);
        return this;
    }

    public ResponseBuilder add(String name, Object value)
    {
        responseMap.put(name, value);
        return this;
    }

    public Map build()
    {
        return responseMap;
    }
}
