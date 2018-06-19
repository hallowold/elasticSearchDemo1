//package com.example.demo.test;
//
//import net.minidev.json.JSONObject;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.search.aggregations.AggregationBuilder;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
//import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
//import org.elasticsearch.search.aggregations.metrics.percentiles.Percentiles;
//import org.elasticsearch.search.aggregations.metrics.stats.Stats;
//import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
//
//import java.util.*;
//
//public class GroupBy {
//
//    //老版本search对象？
//    private SearchRequestBuilder search;
//
//    //search对象
//    private SearchResponse response;
//
//    //聚合名
//    private String termsName;
//
//    //聚合的字段名
//    private AggregationBuilder termsBuilder;
//
//    //aggsMap的List
//    private List<Map<String, Object>> subAggList = new ArrayList<Map<String, Object>>();
//
//    public GroupBy(SearchRequestBuilder search, SearchResponse response, String termsName, String fieldName, boolean asc) {
//        this.search = search;
//        this.response = response;
//        this.termsName = termsName;
//        termsBuilder = AggregationBuilders.terms(termsName).field(fieldName).order(Terms.Order.term(asc)).size(0);
//    }
//
//    //增加aggsList
//    private void addSubAggList(String aggName, AggregationBuilder aggBuilder) {
//        Map<String, Object> subAgg = new HashMap<String, Object>();
//        subAgg.put("aggName", aggName);
//        subAgg.put("aggBuilder", aggBuilder);
//        subAggList.add(subAgg);
//    }
//
//    public void addSumAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.sum(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketSumAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof SumBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addCountAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.count(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketCountAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof ValueCountBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addAvgAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.avg(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketAvgAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof AvgBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addMinAgg(String aggName, String fieldName) {
//        MinBuilder builder = AggregationBuilders.min(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketMinAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof MinBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addMaxAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.max(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketMaxAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof MaxBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addStatsAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.stats(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketStatsAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof StatsBuilder) {
//            Stats stats = bucket.getAggregations().get(aggName);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("min", stats.getMin());
//            jsonObject.put("max", stats.getMax());
//            jsonObject.put("sum", stats.getMax());
//            jsonObject.put("count", stats.getCount());
//            jsonObject.put("avg", stats.getAvg());
//            tmpMap.put(aggName, jsonObject.toJSONString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addExtendedStatsAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.extendedStats(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketExtendedStatsAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof ExtendedStatsBuilder) {
//            ExtendedStats extendedStats = bucket.getAggregations().get(aggName);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("min", extendedStats.getMin());
//            jsonObject.put("max", extendedStats.getMax());
//            jsonObject.put("sum", extendedStats.getMax());
//            jsonObject.put("count", extendedStats.getCount());
//            jsonObject.put("avg", extendedStats.getAvg());
//            jsonObject.put("stdDeviation", extendedStats.getStdDeviation());
//            jsonObject.put("sumOfSquares", extendedStats.getSumOfSquares());
//            jsonObject.put("variance", extendedStats.getVariance());
//            tmpMap.put(aggName, jsonObject.toJSONString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addPercentilesAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.percentiles(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public void addPercentilesAgg(String aggName, String fieldName, double[] percentiles) {
//        AggregationBuilder builder = AggregationBuilders.percentiles(aggName).field(fieldName).percentiles(percentiles);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketPercentilesAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof PercentilesBuilder) {
//            Percentiles percentiles = bucket.getAggregations().get(aggName);
//            JSONObject jsonObject = new JSONObject();
//            for (Percentile percentile : percentiles) {
//                jsonObject.put(String.valueOf(percentile.getPercent()), percentile.getValue());
//            }
//            tmpMap.put(aggName, jsonObject.toJSONString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addPercentileRanksAgg(String aggName, String fieldName, double[] percentiles) {
//        AggregationBuilder builder = AggregationBuilders.percentileRanks(aggName).field(fieldName).percentiles(percentiles);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketPercentileRanksAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof PercentileRanksBuilder) {
//            PercentileRanks percentileRanks = bucket.getAggregations().get(aggName);
//            JSONObject jsonObject = new JSONObject();
//            for (Percentile percentile : percentileRanks) {
//                jsonObject.put(String.valueOf(percentile.getPercent()), percentile.getValue());
//            }
//            tmpMap.put(aggName, jsonObject.toJSONString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void addCardinalityAgg(String aggName, String fieldName) {
//        AggregationBuilder builder = AggregationBuilders.cardinality(aggName).field(fieldName);
//        termsBuilder.subAggregation(builder);
//        addSubAggList(aggName, builder);
//    }
//
//    public boolean bucketCardinalityAgg(Terms.Bucket bucket, String aggName, AggregationBuilder aggBuilder, Map<String, String> tmpMap) {
//        if (aggBuilder instanceof CardinalityBuilder) {
//            tmpMap.put(aggName, bucket.getAggregations().get(aggName).getProperty("value").toString());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public List<Terms.Bucket> getTermsBucket() {
//        search.addAggregation(termsBuilder);
//        Terms termsGroup = search.get().getAggregations().get(termsName);
//        return termsGroup.getBuckets();
//    }
//
//    public Map<String, Object> getGroupbyResponse() {
//        Map<String, Object> aggResponseMap = new TreeMap<String, Object>();
//        for (Terms.Bucket bucket : getTermsBucket()) {
//            String bucketKeyAsString = bucket.getKeyAsString();
//            Map<String, String> tmpMap = new TreeMap<String, String>();
//            for (Map<String, Object> subAgg : subAggList) {
//                String subAggName = subAgg.get("aggName").toString();
//                AggregationBuilder subAggBuilder = (MetricsAggregationBuilder) subAgg.get("aggBuilder");
//                if (bucketAvgAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketMaxAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketMinAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketSumAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketCountAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketCardinalityAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketPercentileRanksAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketPercentilesAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketExtendedStatsAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//                if (bucketStatsAgg(bucket, subAggName, subAggBuilder, tmpMap)) continue;
//            }
//            aggResponseMap.put(bucketKeyAsString, tmpMap);
//        }
//        return aggResponseMap;
//    }
//}
