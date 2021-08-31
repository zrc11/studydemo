package com.swagger.demo.controller;

import com.swagger.demo.model.bo.ProductDetailBO;
import com.swagger.demo.model.vo.DeleteProductVO;
import com.swagger.demo.response.BaseResponse;
import com.swagger.demo.response.RespGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController类
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
 * <td>2021/8/25 17:52</td>
 * <td>zrc</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author zrc
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = "产品接口")
@RestController
@RequestMapping("/productController")
public class ProductController {

    @ApiOperation(value = "获取产品详情信息")
    @GetMapping("/getProductDetail")
    @ApiImplicitParam(name = "pid", value = "产品id", paramType = "String")
    public BaseResponse<ProductDetailBO> getProductDetail(@RequestParam(value = "pid") String pid) {
        return RespGenerator.returnOK("成功");
    }

    @ApiOperation(value = "获取产品列表信息")
    @PostMapping("/getProductList")
    public BaseResponse<List<ProductDetailBO>> getProductList() {
        return RespGenerator.returnOK("成功");
    }

    @ApiOperation(value = "删除产品")
    @PostMapping("/deleteProductList")
    public BaseResponse<Integer> deleteProductList(@RequestBody DeleteProductVO deleteProductVO) {
        return RespGenerator.returnOK("成功");
    }

}
