package com.lsp.crm.settings.service.Impl;

import com.lsp.crm.settings.mapper.DicValueMapper;
import com.lsp.crm.settings.pojo.DicValue;
import com.lsp.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("dicValueServiceImpl")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;
    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
