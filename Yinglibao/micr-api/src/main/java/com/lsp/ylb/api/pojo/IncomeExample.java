package com.lsp.ylb.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IncomeExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IncomeExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBidIdIsNull() {
            addCriterion("bid_id is null");
            return (Criteria) this;
        }

        public Criteria andBidIdIsNotNull() {
            addCriterion("bid_id is not null");
            return (Criteria) this;
        }

        public Criteria andBidIdEqualTo(Integer value) {
            addCriterion("bid_id =", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotEqualTo(Integer value) {
            addCriterion("bid_id <>", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdGreaterThan(Integer value) {
            addCriterion("bid_id >", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bid_id >=", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdLessThan(Integer value) {
            addCriterion("bid_id <", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdLessThanOrEqualTo(Integer value) {
            addCriterion("bid_id <=", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdIn(List<Integer> values) {
            addCriterion("bid_id in", values, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotIn(List<Integer> values) {
            addCriterion("bid_id not in", values, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdBetween(Integer value1, Integer value2) {
            addCriterion("bid_id between", value1, value2, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bid_id not between", value1, value2, "bidId");
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

        public Criteria andIncomeDateIsNull() {
            addCriterion("income_date is null");
            return (Criteria) this;
        }

        public Criteria andIncomeDateIsNotNull() {
            addCriterion("income_date is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeDateEqualTo(Date value) {
            addCriterionForJDBCDate("income_date =", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("income_date <>", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("income_date >", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("income_date >=", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateLessThan(Date value) {
            addCriterionForJDBCDate("income_date <", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("income_date <=", value, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateIn(List<Date> values) {
            addCriterionForJDBCDate("income_date in", values, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("income_date not in", values, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("income_date between", value1, value2, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("income_date not between", value1, value2, "incomeDate");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyIsNull() {
            addCriterion("income_money is null");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyIsNotNull() {
            addCriterion("income_money is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyEqualTo(BigDecimal value) {
            addCriterion("income_money =", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyNotEqualTo(BigDecimal value) {
            addCriterion("income_money <>", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyGreaterThan(BigDecimal value) {
            addCriterion("income_money >", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("income_money >=", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyLessThan(BigDecimal value) {
            addCriterion("income_money <", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("income_money <=", value, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyIn(List<BigDecimal> values) {
            addCriterion("income_money in", values, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyNotIn(List<BigDecimal> values) {
            addCriterion("income_money not in", values, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_money between", value1, value2, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_money not between", value1, value2, "incomeMoney");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIsNull() {
            addCriterion("income_status is null");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIsNotNull() {
            addCriterion("income_status is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusEqualTo(Integer value) {
            addCriterion("income_status =", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotEqualTo(Integer value) {
            addCriterion("income_status <>", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusGreaterThan(Integer value) {
            addCriterion("income_status >", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_status >=", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusLessThan(Integer value) {
            addCriterion("income_status <", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("income_status <=", value, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusIn(List<Integer> values) {
            addCriterion("income_status in", values, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotIn(List<Integer> values) {
            addCriterion("income_status not in", values, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusBetween(Integer value1, Integer value2) {
            addCriterion("income_status between", value1, value2, "incomeStatus");
            return (Criteria) this;
        }

        public Criteria andIncomeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("income_status not between", value1, value2, "incomeStatus");
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