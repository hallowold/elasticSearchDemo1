package com.example.demo.common.baseDAO;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <pre>
 * 基础dao
 * </pre>
 * JpaSpecificationExecutor 复杂查询
 * CrudRepository 简单查询
 * PagingAndSortingRepository 分页查询
 *
 *
 * Keyword	                Sample	                 JPQL snippet
 * IsNotNull	            findByAgeNotNull	     ...  where x.age not null
 * Like	                    findByNameLike	         ...  where x.name like ?1
 * NotLike	                findByNameNotLike	     ...  where x.name not like ?1
 * StartingWith	            findByNameStartingWith	 ...  where x.name like ?1(parameter bound with appended %)
 * EndingWith	            findByNameEndingWith	 ...  where x.name like ?1(parameter bound with prepended %)
 * Containing	            findByNameContaining	 ...  where x.name like ?1(parameter bound wrapped in %)
 * OrderBy	                findByAgeOrderByName	 ...  where x.age = ?1 order by x.name desc
 * Not	                    findByNameNot	         ...  where x.name <> ?1
 * In	                    findByAgeIn	             ...  where x.age in ?1
 * NotIn	                findByAgeNotIn	         ...  where x.age not in ?1
 * True	                    findByActiveTrue	     ...  where x.avtive = true
 * Flase	                findByActiveFalse	     ...  where x.active = false
 * And 	                    findByNameAndAge	     ...  where x.name = ?1 and x.age = ?2
 * Or	                    findByNameOrAge	         ...  where x.name = ?1 or x.age = ?2
 * Between	                findBtAgeBetween	     ...  where x.age between ?1 and ?2
 * LessThan	                findByAgeLessThan	     ...  where x.age  <  ?1
 * GreaterThan	            findByAgeGreaterThan	 ...  where x.age > ?1
 * After/Before	            ...	...
 * IsNull	                findByAgeIsNull	         ...  where x.age is null
 */
@NoRepositoryBean
public interface BaseDAO<T, ID extends Serializable> extends JpaRepository<T, ID>,CrudRepository<T, ID>,PagingAndSortingRepository<T, ID>,JpaSpecificationExecutor<T>{

    public default List<?> findAllBy(Class<?> clasz, List<DaoCriteria> criterias, String... ignoreFields) {
        return findAllBy(clasz, criterias, new Sort("id"), ignoreFields);
    }

    @SuppressWarnings("unchecked")
    public default List<?> findAllBy(Class<?> clasz, List<DaoCriteria> criterias, Sort sort ,String... ignoreFields) {
        ExampleMatcher matcher = ExampleMatcher.matching();

        try {
            Object o = clasz.newInstance();
            criterias.forEach(c -> {
                try {
                    String propertyName = c.getPropertyName();
                    Method m = clasz.getMethod("set" + String.valueOf(propertyName.charAt(0)).toUpperCase()
                            + propertyName.substring(1), c.getValue().getClass());
                    m.invoke(o, c.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            if (0 != ignoreFields.length) {
                matcher = matcher.withIgnorePaths(ignoreFields);
            }

            Example<T> ex = (Example<T>) Example.of(o, matcher);

            return findAll(ex, sort);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
