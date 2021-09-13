package com.swagger.demo.config;

/**
 * BaseException类（通用异常）
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
 * <td>2021/8/31 9:41</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */

public class BaseException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = -8329283070529101738L;
    /** 错误码 */
    protected String code;
    /** 错误信息 */
    protected String message;

    /**
     * 默认构造方法
     */
    public BaseException() {
        super();
    }

    /**
     * 默认构造方法
     *
     * @param errorInfoInterface
     *            错误返回码
     */
    public BaseException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.code = errorInfoInterface.getResultCode();
        this.message = errorInfoInterface.getResultMsg();
    }

    /**
     *
     * 默认构造方法
     *
     * @param errorInfoInterface
     *            错误返回码
     * @param cause
     *            Throwable
     */
    public BaseException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.code = errorInfoInterface.getResultCode();
        this.message = errorInfoInterface.getResultMsg();
    }

    /**
     * 默认构造方法
     *
     * @param errorMsg
     *            错误信息
     */
    public BaseException(String errorMsg) {
        super(errorMsg);
        this.message = errorMsg;
    }

    /**
     * 默认构造方法
     *
     * @param errorCode
     *            错误码
     * @param errorMsg
     *            错误信息
     */
    public BaseException(String errorCode, String errorMsg) {
        super(errorCode);
        this.code = errorCode;
        this.message = errorMsg;
    }

    /**
     * 默认构造方法
     *
     * @param errorCode
     *            错误码
     * @param errorMsg
     *            错误信息
     * @param cause
     *            Throwable
     */
    public BaseException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.code = errorCode;
        this.message = errorMsg;
    }

    /**
     * 获取类成员code
     *
     * @return {@link #code}
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设定类成员code
     *
     * @param code
     *            要设定的{@link #code}
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取类成员message
     *
     * @return {@link #message}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 设定类成员message
     *
     * @param message
     *            要设定的{@link #message}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
