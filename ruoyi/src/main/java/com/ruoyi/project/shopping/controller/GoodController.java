package com.ruoyi.project.shopping.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.shopping.domain.Good;
import com.ruoyi.project.shopping.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@RestController
@RequestMapping("/shopping/good")
public class GoodController extends BaseController
{
    @Autowired
    private IGoodService goodService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:good:list')")
    @GetMapping("/list")
    public TableDataInfo list(Good good)
    {
        startPage();
        List<Good> list = goodService.selectGoodList(good);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:good:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Good good)
    {
        List<Good> list = goodService.selectGoodList(good);
        ExcelUtil<Good> util = new ExcelUtil<Good>(Good.class);
        return util.exportExcel(list, "good");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:good:query')")
    @GetMapping(value = "/{goodId}")
    public AjaxResult getInfo(@PathVariable("goodId") Long goodId)
    {
        return AjaxResult.success(goodService.selectGoodById(goodId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('system:good:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Good good)
    {
        return toAjax(goodService.insertGood(good));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('system:good:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Good good)
    {
        return toAjax(goodService.updateGood(good));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('system:good:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodIds}")
    public AjaxResult remove(@PathVariable Long[] goodIds)
    {
        return toAjax(goodService.deleteGoodByIds(goodIds));
    }
}
