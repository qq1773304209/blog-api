package com.jonm.respond;



public class ResultUtil {

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功有返回
     */
    public static Result success(Object object) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    /**
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功无返回
     */
    public static Result success() {
        return ResultUtil.success(null);
    }

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 参数不合法返回
     */
    public static Result paramError(Object object) {
        Result result = new Result(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        result.setData(object);
        return result;
    }

    /**
     * @return Result 返回类型
     * @Title: paramError
     * @Description: 参数不合法返回
     */
    public static Result paramError() {
        return new Result(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
    }

    /**
     * 返回自定义状态码及msg
     * @param code
     * @param msg
     * @return
     */
    public static Result customResultParam(String code, String msg) {
        return new Result(code, msg);
    }
    /**
     * @return Result 返回类型
     * @Title: paramError
     * @Description: 数据库错误
     */
    public static Result dataBaseError() {
        return new Result(ResultEnum.DATABASE_ERROR.getCode(), ResultEnum.DATABASE_ERROR.getMessage());
    }
    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 失败方法返回
     */
    public static Result error(String code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }

    public static Result error() {
        return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMessage());
    }


    public static ErrorResult except(ResultEnum resultEnum, Throwable e, String msg){
        ErrorResult errorResult = ResultUtil.except(resultEnum, e);
        errorResult.setMsg(msg);
        return errorResult;
    }

    public static ErrorResult except(ResultEnum resultEnum, Throwable e){
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(resultEnum.getCode());
        errorResult.setMsg(resultEnum.getMessage());
        errorResult.setException(e.getClass().getName());
        return errorResult;
    }

    public static Result except(String code, String msg){
        return new Result(code, msg);
    }

}
