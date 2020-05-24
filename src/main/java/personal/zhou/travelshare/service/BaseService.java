package personal.zhou.travelshare.service;


import personal.zhou.travelshare.util.Result;

public class BaseService {

    public Result getResult(int code, Object o, String msg) {

        Result result = new Result();
        result.setCode(code);
        result.setObject(o);
        result.setMessage(msg);

        return result;
    }

}
