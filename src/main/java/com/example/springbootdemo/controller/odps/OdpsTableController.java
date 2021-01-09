package com.example.springbootdemo.controller.odps;

import com.example.springbootdemo.common.db.dao.odps.model.Odps;
import com.example.springbootdemo.controller.params.MyResult;
import com.example.springbootdemo.service.OdpsTableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/odps")
public class OdpsTableController {

    @Autowired
    private OdpsTableService odpsTableService;

    @RequestMapping("/queryOdpsByKeyword")
    public MyResult queryOdpsByKeyword(ModelMap modelMap, HttpServletRequest request, String keyword, Integer pageIndex, Integer pageSize) {
        PageInfo<Odps> odpsPageInfo = odpsTableService.queryOdpsTableByKeyword(keyword, pageSize, pageIndex);
        MyResult<PageInfo<Odps>> result = new MyResult<>();
//        Integer total = odpsTableService.queryOdpsTotalCountByKeyword(keyword);
//        odpsPageInfo.setTotal(total);
        result.setData(odpsPageInfo);
        result.setSuccess(true);
        return result;
    }
}
