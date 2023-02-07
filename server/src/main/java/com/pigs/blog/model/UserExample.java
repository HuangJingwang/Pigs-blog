package com.pigs.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andGithubIdIsNull() {
            addCriterion("github_id is null");
            return (Criteria) this;
        }

        public Criteria andGithubIdIsNotNull() {
            addCriterion("github_id is not null");
            return (Criteria) this;
        }

        public Criteria andGithubIdEqualTo(Long value) {
            addCriterion("github_id =", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdNotEqualTo(Long value) {
            addCriterion("github_id <>", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdGreaterThan(Long value) {
            addCriterion("github_id >", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdGreaterThanOrEqualTo(Long value) {
            addCriterion("github_id >=", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdLessThan(Long value) {
            addCriterion("github_id <", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdLessThanOrEqualTo(Long value) {
            addCriterion("github_id <=", value, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdIn(List<Long> values) {
            addCriterion("github_id in", values, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdNotIn(List<Long> values) {
            addCriterion("github_id not in", values, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdBetween(Long value1, Long value2) {
            addCriterion("github_id between", value1, value2, "githubId");
            return (Criteria) this;
        }

        public Criteria andGithubIdNotBetween(Long value1, Long value2) {
            addCriterion("github_id not between", value1, value2, "githubId");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1IsNull() {
            addCriterion("question_answer1 is null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1IsNotNull() {
            addCriterion("question_answer1 is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1EqualTo(String value) {
            addCriterion("question_answer1 =", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1NotEqualTo(String value) {
            addCriterion("question_answer1 <>", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1GreaterThan(String value) {
            addCriterion("question_answer1 >", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1GreaterThanOrEqualTo(String value) {
            addCriterion("question_answer1 >=", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1LessThan(String value) {
            addCriterion("question_answer1 <", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1LessThanOrEqualTo(String value) {
            addCriterion("question_answer1 <=", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1Like(String value) {
            addCriterion("question_answer1 like", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1NotLike(String value) {
            addCriterion("question_answer1 not like", value, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1In(List<String> values) {
            addCriterion("question_answer1 in", values, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1NotIn(List<String> values) {
            addCriterion("question_answer1 not in", values, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1Between(String value1, String value2) {
            addCriterion("question_answer1 between", value1, value2, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer1NotBetween(String value1, String value2) {
            addCriterion("question_answer1 not between", value1, value2, "questionAnswer1");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2IsNull() {
            addCriterion("question_answer2 is null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2IsNotNull() {
            addCriterion("question_answer2 is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2EqualTo(String value) {
            addCriterion("question_answer2 =", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2NotEqualTo(String value) {
            addCriterion("question_answer2 <>", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2GreaterThan(String value) {
            addCriterion("question_answer2 >", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2GreaterThanOrEqualTo(String value) {
            addCriterion("question_answer2 >=", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2LessThan(String value) {
            addCriterion("question_answer2 <", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2LessThanOrEqualTo(String value) {
            addCriterion("question_answer2 <=", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2Like(String value) {
            addCriterion("question_answer2 like", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2NotLike(String value) {
            addCriterion("question_answer2 not like", value, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2In(List<String> values) {
            addCriterion("question_answer2 in", values, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2NotIn(List<String> values) {
            addCriterion("question_answer2 not in", values, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2Between(String value1, String value2) {
            addCriterion("question_answer2 between", value1, value2, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andQuestionAnswer2NotBetween(String value1, String value2) {
            addCriterion("question_answer2 not between", value1, value2, "questionAnswer2");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNull() {
            addCriterion("update_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNotNull() {
            addCriterion("update_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtEqualTo(Date value) {
            addCriterion("update_at =", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotEqualTo(Date value) {
            addCriterion("update_at <>", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThan(Date value) {
            addCriterion("update_at >", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("update_at >=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThan(Date value) {
            addCriterion("update_at <", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThanOrEqualTo(Date value) {
            addCriterion("update_at <=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIn(List<Date> values) {
            addCriterion("update_at in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotIn(List<Date> values) {
            addCriterion("update_at not in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtBetween(Date value1, Date value2) {
            addCriterion("update_at between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotBetween(Date value1, Date value2) {
            addCriterion("update_at not between", value1, value2, "updateAt");
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