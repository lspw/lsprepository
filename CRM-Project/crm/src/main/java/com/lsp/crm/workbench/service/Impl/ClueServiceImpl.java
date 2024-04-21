package com.lsp.crm.workbench.service.Impl;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.commons.utils.UUIDUtils;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.workbench.mapper.*;
import com.lsp.crm.workbench.pojo.*;
import com.lsp.crm.workbench.service.ClueRemarkService;
import com.lsp.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactRemarkMapper contactRemarkMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private ContactActivityRelationMapper contactActivityRelationMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue queryClueDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }


    /*保存转换的各种信息*/
    @Override
    public void saveCovert(Map<String, Object> map) {
        String clueId=(String) map.get("clueId");
        String isCreateTran = (String) map.get("isCreateTran");
        User user=(User) map.get(Contants.SESSION_USER);
        //根据id查询线索信息
        Clue clue=clueMapper.selectClueById(clueId);
        Customer customer=new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(clue.getCreateTime());
        customer.setDescription(clue.getDescription());
        customer.setId(UUIDUtils.getUuid());
        customer.setName(clue.getCompany());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setOwner(user.getId());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        customerMapper.insertCustomer(customer);
        //把线索中有关联系人的信息转换到联系人表中
        Contacts contacts=new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setDescription(clue.getDescription());
        contacts.setAppellation(clue.getAppellation());
        contacts.setId(UUIDUtils.getUuid());
        contacts.setCreateBy(user.getId());
        contacts.setCustomerId(customer.getId());
        contacts.setCreateTime(clue.getCreateTime());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setSource(clue.getSource());
        contacts.setOwner(user.getId());
        contactsMapper.insertContacts(contacts);

        //根id的查询线索下的所有备注
        List<ClueRemark> remarkList = clueRemarkMapper.selectClueRemarkByClueId(clueId);

        //判断是否为空
        CustomerRemark cur=null;
        ContactRemark cor=null;
        if (remarkList!=null&&remarkList.size()>0){
            /*把该线索下所有的备注转化到客户备注表下,把该线索下的所有备注转换到联系人备注下*/
            List<CustomerRemark> curList=new ArrayList<>();
            List<ContactRemark> corList=new ArrayList<>();
            for(ClueRemark cr:remarkList){
                cur=new CustomerRemark();
                cur.setCreateBy(cr.getCreateBy());
                cur.setCreateTime(cr.getCreateTime());
                cur.setId(UUIDUtils.getUuid());
                cur.setCustomerId(customer.getId());
                cur.setEditBy(cr.getEditBy());
                cur.setEditTime(cr.getEditTime());
                cur.setEditFlag(cr.getEditFlag());
                cur.setNoteContent(cr.getNoteContent());
                curList.add(cur);

                cor=new ContactRemark();
                cor.setContactsId(contacts.getId());
                cor.setCreateBy(cr.getCreateBy());
                cor.setId(UUIDUtils.getUuid());
                cor.setCreateTime(cr.getCreateTime());
                cor.setEditFlag(cr.getEditFlag());
                cor.setEditBy(cr.getEditBy());
                cor.setNoteContent(cr.getNoteContent());
                cor.setEditTime(cr.getEditTime());
                corList.add(cor);
            }
            customerRemarkMapper.insertCustomerRemarkByList(curList);
            contactRemarkMapper.insertContactRemarkByList(corList);
        }
        //根据ClueId查询市场活动和线索的关联关系
        List<ClueActivityRelation> carList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        //把该线索和市场活动的关联关系，转换到联系人和市场活动的关联关系表中
        if (carList!=null&&carList.size()>0){
            ContactActivityRelation  coar=null;
            List<ContactActivityRelation> coarList=new ArrayList<>();
            for (ClueActivityRelation car:carList){
              coar=new ContactActivityRelation();
              coar.setActivityId(car.getActivityId());
              coar.setId(UUIDUtils.getUuid());
              coar.setContactsId(contacts.getId());
              coarList.add(coar);
            }
           contactActivityRelationMapper.insertContactsActivityRelationByList(coarList);
        }
        //判断是否需要创建交易,不为空向交易表中添加记录  ，奖项所表中的备注记录转移一份到交易表下
        if ("true".equals(isCreateTran)){
            Tran tran=new Tran();
            tran.setActivityId((String) map.get("activityId"));
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateFormatUtils.formatUtil(new Date()));
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setId(UUIDUtils.getUuid());
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setOwner(user.getId());
            tran.setStage((String) map.get("stage"));
            tranMapper.insertTran(tran);

            if (remarkList!=null&&remarkList.size()>0){
                List<TranRemark> trList=new ArrayList<>();
                TranRemark tr=null;
                for (ClueRemark cr:remarkList){
                   tr=new TranRemark();
                   tr.setCreateBy(cr.getCreateBy());
                   tr.setCreateTime(cr.getCreateTime());
                   tr.setEditBy(cr.getEditBy());
                   tr.setEditTime(cr.getEditTime());
                   tr.setEditFlag(cr.getEditFlag());
                   tr.setId(UUIDUtils.getUuid());
                   tr.setNoteContent(cr.getNoteContent());
                   tr.setTranId(tran.getId());
                   trList.add(tr);
                }
                tranRemarkMapper.insertTranRemarkByList(trList);
            }
        }
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        clueMapper.deleteClueById(clueId);
    }

    @Override
    public List<Clue> queryAllClue() {
        return clueMapper.selectAllClue();
    }

    @Override
    public List<Clue> queryClueByCondition(Map<String,Object> map)  {
        return clueMapper.selectClueByCondition(map);
    }

    @Override
    public int queryClueCountByCondition(Map<String, Object> map) {
        return clueMapper.selectClueCountByCondition(map);
    }
}
