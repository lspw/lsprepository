package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {
    //添加线索
    int saveCreateClue(Clue clue);

    //查看线索明细
    Clue queryClueDetailById(String id);

    void saveCovert(Map<String,Object> map);

    /**
     * 查询所有的线索
     * @return
     */
    List<Clue> queryAllClue();

    /**
     * 根据条件查询线索
     * @return
     */
    List<Clue> queryClueByCondition(Map<String,Object> map);
    /**
     * 根据条件查询线索条数
     * @return
     */
    int queryClueCountByCondition(Map<String,Object> map);
}
