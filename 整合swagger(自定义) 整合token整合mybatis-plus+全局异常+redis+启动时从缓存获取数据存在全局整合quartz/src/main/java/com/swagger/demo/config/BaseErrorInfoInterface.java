package com.swagger.demo.config;

/**
 * 此接口用于返回码枚举使用
 * <p>
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2021/8/31 9:39</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     *
     * @return
     */
    String getResultCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getResultMsg();
}
