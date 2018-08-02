package com.example.demo.common.baseDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
//import org.hibernate.jpa.criteria.predicate.InPredicate;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.predicate.InPredicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * 制作自定义查询条件的基础类，因业务无需求，各条件统一使用and连接
 * @author liuqitian
 * @date 2018年8月1日
 */
public class BaseQuerySpecification<T> implements Specification<T> {

    private List<DaoCriteria> daoCriterias;

    private Map<String, List<DaoCriteria>> criterias;

    private Map<String, List<String>> orderbys;

    public BaseQuerySpecification(List<DaoCriteria> daoCriterias) {
        this.daoCriterias = daoCriterias;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if(daoCriterias == null){
            return builder.and(new Predicate[]{});
        }
        criterias = classify(daoCriterias);
        List<Predicate> conditions = this.setConditions(root, query, builder);
        this.changeWhere(query, builder, conditions);
        this.changeOrderBy(root, query, builder);
        return query.getRestriction();
    }

    public List<Predicate> setConditions(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> conditions = new ArrayList<Predicate>();
        for(String key: criterias.keySet()) {
            List<DaoCriteria> daoCriterias = criterias.get(key);
            if (daoCriterias.size() > 1) {
                List<Predicate> sameConditions = new ArrayList<Predicate>();
                for(DaoCriteria daoCriteria: daoCriterias) {
                    Predicate predicate = createPredicate(root, query, builder, daoCriteria);
                    if (null == predicate) {
                        throw new IllegalArgumentException("提供的查询参数格式不正确");
                    } else {
                        sameConditions.add(predicate);
                    }
                }
                Predicate[] predicates = new Predicate[sameConditions.size()];
                conditions.add(builder.and(sameConditions.toArray(predicates)));
            } else {
                for(DaoCriteria daoCriteria: daoCriterias) {
                    Predicate predicate = createPredicate(root, query, builder, daoCriteria);
                    if (null != predicate) {
                        conditions.add(predicate);
                    }
                }
            }
        }
        return conditions;
    }

    public void changeWhere(CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> conditions) {
        query.where(builder.and(conditions.toArray(new Predicate[conditions.size()])));
    }

    public void changeOrderBy(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Order> orders = new ArrayList<>();
        orderbys.keySet().forEach(key -> {
            if (key.toLowerCase().equals("asc") && !orderbys.get(key).isEmpty()) {
                orderbys.get(key).forEach(propertyName -> {
                    orders.add(builder.asc(root.get(propertyName).as(String.class)));
                });
            } else if (key.toLowerCase().equals("desc") && !orderbys.get(key).isEmpty()) {
                orderbys.get(key).forEach(propertyName -> {
                    orders.add(builder.desc(root.get(propertyName).as(String.class)));
                });
            }
        });
        query.orderBy(orders);
    }

    /**
     * 解析传入的daoCriteria
     */
    private Map<String, List<DaoCriteria>> classify(List<DaoCriteria> criteraList) {
        Map<String, List<DaoCriteria>> criteraType = new HashMap<String, List<DaoCriteria>>();
        orderbys = new HashMap<>();
        orderbys.put("asc", new ArrayList<String>());
        orderbys.put("desc", new ArrayList<String>());
        if (criteraList != null && criteraList.size() > 0) {
            for (DaoCriteria daoCriteria : criteraList) {
                if (daoCriteria.getOperator().equals(QueryOperatorEnum.ORDER_BY.getName())) {
                    orderbys.get(daoCriteria.getValue().toString().toLowerCase()).add(daoCriteria.getPropertyName());
                } else {
                    criteraType.computeIfAbsent(daoCriteria.getPropertyName(), k -> new ArrayList<DaoCriteria>())
                            .add(daoCriteria);
                }
            }
        }
        return criteraType;
    }

    private Predicate createPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, DaoCriteria daoCriteria) {
        String name = daoCriteria.getPropertyName();
        Object value = daoCriteria.getValue();
        String operator = daoCriteria.getOperator();

        if (operator.equals(QueryOperatorEnum.LIKE.getName())) {
            return builder.like(root.get(name).as(String.class), value.toString());
        } else if (operator.equals(QueryOperatorEnum.EQUAL.getName())) {
            return builder.equal(root.get(name).as(String.class), value);
        } else if (operator.equals(QueryOperatorEnum.NOT_EQUAL.getName())) {
            return builder.notEqual(root.get(name).as(String.class), value);
        } else if (operator.equals(QueryOperatorEnum.LESS.getName())) {
            return builder.lessThan(root.get(name).as(String.class), value.toString());
        } else if (operator.equals(QueryOperatorEnum.BIGGER.getName())) {
            return builder.greaterThan(root.get(name).as(String.class), value.toString());
        } else if (operator.equals(QueryOperatorEnum.LESS_EQUAL.getName())) {
            return builder.lessThanOrEqualTo(root.get(name).as(String.class), value.toString());
        } else if (operator.equals(QueryOperatorEnum.BIGGER_EQUAL.getName())) {
            return builder.greaterThanOrEqualTo(root.get(name).as(String.class), value.toString());
        } else if (operator.equals(QueryOperatorEnum.IN.getName())) {
            return builder.and(new InPredicate<Object>((CriteriaBuilderImpl) builder, root.get(name).as(String.class), value));
        } else if (operator.equals(QueryOperatorEnum.NOT_IN.getName())) {
            return builder.not(new InPredicate<Object>((CriteriaBuilderImpl) builder, root.get(name).as(String.class), value));
        } else {
            return null;
        }
    }
}