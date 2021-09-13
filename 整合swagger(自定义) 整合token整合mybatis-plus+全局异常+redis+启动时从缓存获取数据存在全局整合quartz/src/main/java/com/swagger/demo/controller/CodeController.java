package com.swagger.demo.controller;

import com.swagger.demo.response.BaseResponse;
import com.swagger.demo.response.RespGenerator;
import com.swagger.demo.service.CodeService;
import com.swagger.demo.task.GetRedisData;
import com.swagger.demo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController类
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
 * <td>2021/8/25 17:51</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = "热点数据接口")
@RestController
@RequestMapping("codeController")
public class CodeController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private CodeService codeService;

    @ApiOperation(value = "获取首页热点数据")
    @PostMapping("/getHomeHotData")
    public BaseResponse<Map<String,String>> getHomeHotData() {
        //从redis中获取值
        Map<String,String> hash = new HashMap<>();
        hash.put("news",redisUtils.get("news"));
        hash.put("userNumber",redisUtils.get("userNumber"));
        hash.put("ranking",redisUtils.get("ranking"));
        //从缓存中获取值
        Map<String, String> codes = GetRedisData.codes;
        hash.put("codes",codes.toString());
        return RespGenerator.returnOK(hash);
    }

    @ApiOperation(value = "获取用户人数")
    @PostMapping("/getUserNumber")
    public BaseResponse<String> getUserNumber() {
        return RespGenerator.returnOK(codeService.getUserNumber());
    }

}
