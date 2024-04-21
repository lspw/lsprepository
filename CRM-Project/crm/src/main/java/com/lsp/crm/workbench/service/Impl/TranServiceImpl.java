package com.lsp.crm.workbench.service.Impl;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.commons.utils.UUIDUtils;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.workbench.mapper.CustomerMapper;
import com.lsp.crm.workbench.mapper.TranHistoryMapper;
import com.lsp.crm.workbench.mapper.TranMapper;
import com.lsp.crm.workbench.pojo.Customer;
import com.lsp.crm.workbench.pojo.FunnelVO;
import com.lsp.crm.workbench.pojo.Tran;
import com.lsp.crm.workbench.pojo.TranHistory;
import com.lsp.crm.workbench.service.CustomerService;
import com.lsp.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("tranService")
public class TranServiceImpl implements TranService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private  TranHistoryMapper tranHistoryMapper;

    @Override
    public void queryCustomerByName(Map<String,Object> map) {
        String customerName=(String) map.get("customerName");
        User user =(User) map.get(Contants.SESSION_USER);
        Customer customer= customerMapper.selectCustomerByName(customerName);
        //判断是否查到了
        if (customer==null){
            customer=new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUuid());
            customer.setCreateTime(DateFormatUtils.formatUtil(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //保存创建的交易
        Tran tran=new Tran();
        tran.setStage((String) map.get("stage"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setId(UUIDUtils.getUuid());
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateFormatUtils.formatUtil(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        tranMapper.insertTran(tran);
        //保存历史数据
        TranHistory tranHistory=new TranHistory();
        tranHistory.setCreateBy(user.getId());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateTime(DateFormatUtils.formatUtil(new Date()));
        tranHistory.setId(UUIDUtils.getUuid());
        tranHistoryMapper.insertTranHistory(tranHistory);

    }

    @Override
    public Tran queryTranForDetailById(String id) {
        return tranMapper.selectTranForDetailById(id);
    }

    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }
}
