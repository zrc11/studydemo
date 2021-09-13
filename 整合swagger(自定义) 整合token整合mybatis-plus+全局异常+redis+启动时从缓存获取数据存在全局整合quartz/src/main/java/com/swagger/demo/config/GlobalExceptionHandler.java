package com.swagger.demo.config;

import com.swagger.demo.response.BaseResponse;
import com.swagger.demo.response.RespGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionHandler类（全局异常处理）
 *
 * <p>
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2021/8/31 9:35</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** 静态变量：系统日志 */
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResponse<Object> baseExceptionHandler(HttpServletRequest req, BaseException e) {
        logger.error("发生业务异常！原因是：" + e.getMessage());
        return RespGenerator.returnError(e.getCode(), e.getMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public BaseResponse<Object> exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return RespGenerator.returnError(BaseErrorEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理请求方法不支持的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public BaseResponse<Object> exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        logger.error("发生请求方法不支持异常！原因是:", e);
        return RespGenerator.returnError(BaseErrorEnum.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse<Object> exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return RespGenerator.returnError(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }

}
