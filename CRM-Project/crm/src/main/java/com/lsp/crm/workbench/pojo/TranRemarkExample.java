package com.lsp.crm.workbench.pojo;

import java.util.ArrayList;
import java.util.List;

public class TranRemarkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TranRemarkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNoteContentIsNull() {
            addCriterion("note_content is null");
            return (Criteria) this;
        }

        public Criteria andNoteContentIsNotNull() {
            addCriterion("note_content is not null");
            return (Criteria) this;
        }

        public Criteria andNoteContentEqualTo(String value) {
            addCriterion("note_content =", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotEqualTo(String value) {
            addCriterion("note_content <>", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentGreaterThan(String value) {
            addCriterion("note_content >", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentGreaterThanOrEqualTo(String value) {
            addCriterion("note_content >=", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLessThan(String value) {
            addCriterion("note_content <", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLessThanOrEqualTo(String value) {
            addCriterion("note_content <=", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentLike(String value) {
            addCriterion("note_content like", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotLike(String value) {
            addCriterion("note_content not like", value, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentIn(List<String> values) {
            addCriterion("note_content in", values, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotIn(List<String> values) {
            addCriterion("note_content not in", values, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentBetween(String value1, String value2) {
            addCriterion("note_content between", value1, value2, "noteContent");
            return (Criteria) this;
        }

        public Criteria andNoteContentNotBetween(String value1, String value2) {
            addCriterion("note_content not between", value1, value2, "noteContent");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andEditByIsNull() {
            addCriterion("edit_by is null");
            return (Criteria) this;
        }

        public Criteria andEditByIsNotNull() {
            addCriterion("edit_by is not null");
            return (Criteria) this;
        }

        public Criteria andEditByEqualTo(String value) {
            addCriterion("edit_by =", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByNotEqualTo(String value) {
            addCriterion("edit_by <>", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByGreaterThan(String value) {
            addCriterion("edit_by >", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByGreaterThanOrEqualTo(String value) {
            addCriterion("edit_by >=", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByLessThan(String value) {
            addCriterion("edit_by <", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByLessThanOrEqualTo(String value) {
            addCriterion("edit_by <=", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByLike(String value) {
            addCriterion("edit_by like", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByNotLike(String value) {
            addCriterion("edit_by not like", value, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByIn(List<String> values) {
            addCriterion("edit_by in", values, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByNotIn(List<String> values) {
            addCriterion("edit_by not in", values, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByBetween(String value1, String value2) {
            addCriterion("edit_by between", value1, value2, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditByNotBetween(String value1, String value2) {
            addCriterion("edit_by not between", value1, value2, "editBy");
            return (Criteria) this;
        }

        public Criteria andEditTimeIsNull() {
            addCriterion("edit_time is null");
            return (Criteria) this;
        }

        public Criteria andEditTimeIsNotNull() {
            addCriterion("edit_time is not null");
            return (Criteria) this;
        }

        public Criteria andEditTimeEqualTo(String value) {
            addCriterion("edit_time =", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotEqualTo(String value) {
            addCriterion("edit_time <>", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThan(String value) {
            addCriterion("edit_time >", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("edit_time >=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThan(String value) {
            addCriterion("edit_time <", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThanOrEqualTo(String value) {
            addCriterion("edit_time <=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLike(String value) {
            addCriterion("edit_time like", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotLike(String value) {
            addCriterion("edit_time not like", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeIn(List<String> values) {
            addCriterion("edit_time in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotIn(List<String> values) {
            addCriterion("edit_time not in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeBetween(String value1, String value2) {
            addCriterion("edit_time between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotBetween(String value1, String value2) {
            addCriterion("edit_time not between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditFlagIsNull() {
            addCriterion("edit_flag is null");
            return (Criteria) this;
        }

        public Criteria andEditFlagIsNotNull() {
            addCriterion("edit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEditFlagEqualTo(String value) {
            addCriterion("edit_flag =", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotEqualTo(String value) {
            addCriterion("edit_flag <>", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagGreaterThan(String value) {
            addCriterion("edit_flag >", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("edit_flag >=", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagLessThan(String value) {
            addCriterion("edit_flag <", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagLessThanOrEqualTo(String value) {
            addCriterion("edit_flag <=", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagLike(String value) {
            addCriterion("edit_flag like", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotLike(String value) {
            addCriterion("edit_flag not like", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagIn(List<String> values) {
            addCriterion("edit_flag in", values, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotIn(List<String> values) {
            addCriterion("edit_flag not in", values, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagBetween(String value1, String value2) {
            addCriterion("edit_flag between", value1, value2, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotBetween(String value1, String value2) {
            addCriterion("edit_flag not between", value1, value2, "editFlag");
            return (Criteria) this;
        }

        public Criteria andTranIdIsNull() {
            addCriterion("tran_id is null");
            return (Criteria) this;
        }

        public Criteria andTranIdIsNotNull() {
            addCriterion("tran_id is not null");
            return (Criteria) this;
        }

        public Criteria andTranIdEqualTo(String value) {
            addCriterion("tran_id =", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdNotEqualTo(String value) {
            addCriterion("tran_id <>", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdGreaterThan(String value) {
            addCriterion("tran_id >", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdGreaterThanOrEqualTo(String value) {
            addCriterion("tran_id >=", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdLessThan(String value) {
            addCriterion("tran_id <", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdLessThanOrEqualTo(String value) {
            addCriterion("tran_id <=", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdLike(String value) {
            addCriterion("tran_id like", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdNotLike(String value) {
            addCriterion("tran_id not like", value, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdIn(List<String> values) {
            addCriterion("tran_id in", values, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdNotIn(List<String> values) {
            addCriterion("tran_id not in", values, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdBetween(String value1, String value2) {
            addCriterion("tran_id between", value1, value2, "tranId");
            return (Criteria) this;
        }

        public Criteria andTranIdNotBetween(String value1, String value2) {
            addCriterion("tran_id not between", value1, value2, "tranId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}