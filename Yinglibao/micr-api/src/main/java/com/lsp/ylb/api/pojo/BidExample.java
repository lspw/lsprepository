package com.lsp.ylb.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BidExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BidExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoanIdIsNull() {
            addCriterion("loan_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanIdIsNotNull() {
            addCriterion("loan_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanIdEqualTo(Integer value) {
            addCriterion("loan_id =", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdNotEqualTo(Integer value) {
            addCriterion("loan_id <>", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdGreaterThan(Integer value) {
            addCriterion("loan_id >", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_id >=", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdLessThan(Integer value) {
            addCriterion("loan_id <", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdLessThanOrEqualTo(Integer value) {
            addCriterion("loan_id <=", value, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdIn(List<Integer> values) {
            addCriterion("loan_id in", values, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdNotIn(List<Integer> values) {
            addCriterion("loan_id not in", values, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdBetween(Integer value1, Integer value2) {
            addCriterion("loan_id between", value1, value2, "loanId");
            return (Criteria) this;
        }

        public Criteria andLoanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_id not between", value1, value2, "loanId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andBidMoneyIsNull() {
            addCriterion("bid_money is null");
            return (Criteria) this;
        }

        public Criteria andBidMoneyIsNotNull() {
            addCriterion("bid_money is not null");
            return (Criteria) this;
        }

        public Criteria andBidMoneyEqualTo(BigDecimal value) {
            addCriterion("bid_money =", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyNotEqualTo(BigDecimal value) {
            addCriterion("bid_money <>", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyGreaterThan(BigDecimal value) {
            addCriterion("bid_money >", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bid_money >=", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyLessThan(BigDecimal value) {
            addCriterion("bid_money <", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bid_money <=", value, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyIn(List<BigDecimal> values) {
            addCriterion("bid_money in", values, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyNotIn(List<BigDecimal> values) {
            addCriterion("bid_money not in", values, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid_money between", value1, value2, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid_money not between", value1, value2, "bidMoney");
            return (Criteria) this;
        }

        public Criteria andBidTimeIsNull() {
            addCriterion("bid_time is null");
            return (Criteria) this;
        }

        public Criteria andBidTimeIsNotNull() {
            addCriterion("bid_time is not null");
            return (Criteria) this;
        }

        public Criteria andBidTimeEqualTo(Date value) {
            addCriterion("bid_time =", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeNotEqualTo(Date value) {
            addCriterion("bid_time <>", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeGreaterThan(Date value) {
            addCriterion("bid_time >", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bid_time >=", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeLessThan(Date value) {
            addCriterion("bid_time <", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeLessThanOrEqualTo(Date value) {
            addCriterion("bid_time <=", value, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeIn(List<Date> values) {
            addCriterion("bid_time in", values, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeNotIn(List<Date> values) {
            addCriterion("bid_time not in", values, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeBetween(Date value1, Date value2) {
            addCriterion("bid_time between", value1, value2, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidTimeNotBetween(Date value1, Date value2) {
            addCriterion("bid_time not between", value1, value2, "bidTime");
            return (Criteria) this;
        }

        public Criteria andBidStatusIsNull() {
            addCriterion("bid_status is null");
            return (Criteria) this;
        }

        public Criteria andBidStatusIsNotNull() {
            addCriterion("bid_status is not null");
            return (Criteria) this;
        }

        public Criteria andBidStatusEqualTo(Integer value) {
            addCriterion("bid_status =", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusNotEqualTo(Integer value) {
            addCriterion("bid_status <>", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusGreaterThan(Integer value) {
            addCriterion("bid_status >", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bid_status >=", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusLessThan(Integer value) {
            addCriterion("bid_status <", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bid_status <=", value, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusIn(List<Integer> values) {
            addCriterion("bid_status in", values, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusNotIn(List<Integer> values) {
            addCriterion("bid_status not in", values, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusBetween(Integer value1, Integer value2) {
            addCriterion("bid_status between", value1, value2, "bidStatus");
            return (Criteria) this;
        }

        public Criteria andBidStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bid_status not between", value1, value2, "bidStatus");
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