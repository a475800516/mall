package com.ruoyi.project.shopping.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.shopping.domain.GoodType;
import com.ruoyi.project.shopping.service.IGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类型信息操作处理
 * 
 */
@RestController
@RequestMapping("/shopping/goodType")
public class GoodTypeController extends BaseController
{
    @Autowired
    private IGoodTypeService goodTypeService;

    /**
     * 获取商品类型类型列表
     */
//    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping("/list")
    public AjaxResult list(GoodType goodType)
    {
        List<GoodType> list = goodTypeService.selectGoodTypeList(goodType);
        return AjaxResult.success(list);
    }

    /**
     * 根据商品类型类型编号获取详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{goodTypeId}")
    public AjaxResult getInfo(@PathVariable Long goodTypeId)
    {
        return AjaxResult.success(goodTypeService.selectGoodTypeById(goodTypeId));
    }


    /**
     * 获取商品类型下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(GoodType goodType)
    {
        List<GoodType> list = goodTypeService.selectGoodTypeList(goodType);
        return AjaxResult.success(goodTypeService.buildGoodTypeTreeSelect(list));
    }

    /**
     * 新增商品类型类型
     */
//    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "商品类型类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody GoodType goodType)
    {
        goodType.setCreateBy(SecurityUtils.getUsername());
        return toAjax(goodTypeService.insertGoodType(goodType));
    }

    /**
     * 修改商品类型类型
     */
//    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "商品类型类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody GoodType goodType)
    {
        goodType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(goodTypeService.updateGoodType(goodType));
    }

    /**
     * 删除商品类型类型
     */
//    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "商品类型类型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{goodTypeIds}")
    public AjaxResult remove(@PathVariable Long[] goodTypeIds)
    {
        return toAjax(goodTypeService.deleteGoodTypeByIds(goodTypeIds));
    }

    /**
     * 获取商品类型类型选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<GoodType> goodTypes = goodTypeService.selectGoodTypeAll();
        return AjaxResult.success(goodTypes);
    }
}
