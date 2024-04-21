package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.Clue;
import com.lsp.crm.workbench.pojo.ClueExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ClueMapper {
    long countByExample(ClueExample example);

    int deleteByExample(ClueExample example);

    int deleteByPrimaryKey(String id);

    int insert(Clue record);

    int insertSelective(Clue record);

    List<Clue> selectByExample(ClueExample example);

    Clue selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Clue record, @Param("example") ClueExample example);

    int updateByExample(@Param("record") Clue record, @Param("example") ClueExample example);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    //添加线索
    int insertClue(Clue clue);

    //查询线索明细
    Clue selectClueForDetailById(String id);
    /*
    * 根据id查询线索信息
    * */
    Clue selectClueById(String id);
    /*根据id删除线索*/
    int deleteClueById(String id);


    /**
     * 查询所有的线索
     * @return
     */
    List<Clue> selectAllClue();

    /**
     * 前台传递的参数封装的map集合
     * @param map
     * @return
     */
    List<Clue> selectClueByCondition(Map<String,Object> map);

    /**
     *查询符合条件线索的条数
     * @param map
     * @return
     */
    int selectClueCountByCondition(Map<String,Object> map);
}