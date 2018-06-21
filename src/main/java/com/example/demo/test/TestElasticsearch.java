package com.example.demo.test;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filters.Filters;
import org.elasticsearch.search.aggregations.bucket.filters.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Elasticsearch的基本测试
 * @ClassName: ElasticsearchTest1
 * @author sunt
 * @date 2017年11月22日
 * @version V1.0
 */
public class TestElasticsearch {

    private Logger logger = LoggerFactory.getLogger(TestElasticsearch.class);

    public final static String HOST = "127.0.0.1";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300

    /**
     * 测试Elasticsearch客户端连接
     * @Title: test1
     * @author sunt
     * @date 2017年11月22日
     * @return void
     * @throws UnknownHostException
     */
//    @SuppressWarnings("resource")
//    @Test
//    public void test1() throws UnknownHostException {
//        //创建客户端
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
//                new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
//
//        logger.debug("Elasticsearch connect info:" + client.toString());
//
//        //关闭客户端
//        client.close();
//    }

    private TransportClient client = null;
    /**
     * 获取客户端连接信息
     * @Title: getConnect
     * @author sunt
     * @date 2017年11月23日
     * @return void
     * @throws UnknownHostException
     */
    @SuppressWarnings({ "resource", "unchecked" })
    @Before
    public void getConnect() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
        logger.info("连接信息:" + client.toString());
    }

    /**
     * 关闭连接
     * @Title: closeConnect
     * @author sunt
     * @date 2017年11月23日
     * @return void
     */
    @After
    public void closeConnect() {
        if(null != client) {
            logger.info("执行关闭连接操作...");
            client.close();
        }
    }

    /**
     * CreateDemo
     * @Title: addIndex1
     * @author lqt
     * @date 2018/6/19
     * @return void
     * 需求:创建一个索引库为：msg消息队列,类型为：tweet,并新建一条信息，id需自行输入
     * 注意:索引库的名称必须为小写
     * @throws IOException
     */
    @Test
    public void addIndex1() throws IOException {
        IndexResponse response = client.prepareIndex("msg", "tweet", "2").setSource(XContentFactory.jsonBuilder()
                .startObject().field("userName", "李四")
                .field("sendDate", new Date())
                .field("msg", "你好王五")
                .endObject()).get();

        logger.info("\n索引名称:" + response.getIndex() + "\n类型:" + response.getType()
                + "\n文档ID:" + response.getId() + "\n当前实例状态:" + response.status());
    }

    /**
     * BasicSearchDemo
     * @Title: searchIndex1
     * @author lqt
     * @date 2018/6/1
     * @return void
     * 需求:在msg索引库中，搜索所有信息(matchAll查询)，并循环输出信息
     */
    @Test
    public void searchIndex1() {
        QueryBuilder rangeQueryBuilder = QueryBuilders.matchAllQuery();
        SearchResponse response = client.prepareSearch("msg").setQuery(rangeQueryBuilder).get();
        SearchHits hits = response.getHits();
        System.out.println("查到记录数："+hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length>0){
            for(SearchHit hit:searchHists){
                String id = hit.getId();
                String userName =  (String) hit.getSource().get("userName");
                String msg = (String) hit.getSource().get("msg");
                System.out.println("id: [" + id + "], userName: [" + userName + "], msg: [" + msg + "]");
            }
        }
    }

    /**
     * @Auther: liuqitian
     * @Date: 2018/6/21 14:43
     * @Version: V1.0
     * @Param: []
     * @return: void
     * @Description: 带aggs查询尝试
     */
    @Test
    public void searchIndex2() {
        //matchAll Query
        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery());

        FiltersAggregator.KeyedFilter[] filters = new FiltersAggregator.KeyedFilter[3];
        filters[0] = new FiltersAggregator.KeyedFilter("articleId = 1", QueryBuilders.termQuery("articleId", 1));
        filters[1] = new FiltersAggregator.KeyedFilter("articleId = 2", QueryBuilders.termQuery("articleId", 2));
        filters[2] = new FiltersAggregator.KeyedFilter("articleId = 3", QueryBuilders.termQuery("articleId", 3));
        //create query response
        SearchResponse response = client.prepareSearch("user_article").setQuery(qb).addAggregation(
                AggregationBuilders
                .filters("aggs", filters)
                .subAggregation(AggregationBuilders.topHits("infos"))
        ).execute().actionGet();

        //单独查单参数
//        Global agg = response.getAggregations().get("agg");
//        agg.getDocCount(); // Doc count

        //单独查多参数
//        Filters aggs = response.getAggregations().get("aggs");
//        for (Filters.Bucket entry : aggs.getBuckets()) {
//            String key = entry.getKeyAsString();            // bucket key
//            long docCount = entry.getDocCount();            // Doc count
//            logger.info("key [{}], doc_count [{}]", key, docCount);
//        }

        //同时查询多参数和信息，并按参数分组输出
        Filters aggs = response.getAggregations().get("aggs");
        for (Filters.Bucket entry : aggs.getBuckets()) {
            String key = entry.getKeyAsString();            // bucket key
            long docCount = entry.getDocCount();            // Doc count
            TopHits topHits = entry.getAggregations().get("infos");
            logger.info("key [{}], doc_count [{}]", key, docCount);
            for (SearchHit hit : topHits.getHits().getHits()) {
                logger.info(" -> id [{}], _source [{}]", hit.getId(), hit.getSourceAsString());
            }
        }
    }

    /**
     * @Auther: liuqitian
     * @Date: 2018/6/21 14:42
     * @Version: V1.0
     * @Param: []
     * @return: void
     * @Description: 分组查询尝试
     */
    @Test
    public void searchIndex3() {
        //matchAll Query
        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery());

        //create query response
        SearchResponse response = client.prepareSearch("user_article").setQuery(qb).addAggregation(
                AggregationBuilders
                        .terms("articles")
                        .field("article.name.keyword")
                        .subAggregation(AggregationBuilders.topHits("infos"))
                        .order(Terms.Order.count(false))
        ).execute().actionGet();

        Terms articles = response.getAggregations().get("articles");

        for (Terms.Bucket entry : articles.getBuckets()) {
            entry.getKey();      // Term
            entry.getDocCount(); // Doc count
            TopHits topHits = entry.getAggregations().get("infos");
            logger.info("key [{}], doc_count [{}]", entry.getKey(), entry.getDocCount());
            for (SearchHit hit : topHits.getHits().getHits()) {
                logger.info(" -> id [{}], _source [{}]", hit.getId(), hit.getSourceAsString());
            }
        }

    }

    /**
     * @Auther: liuqitian
     * @Date: 2018/6/21 14:41
     * @Version: V1.0
     * @Param: []
     * @return: void
     * @Description: 分组聚合查询，不带源信息
     */
    @Test
    public void searchTest1() {
        SearchResponse searchResponse = client.prepareSearch("player_count")
                .setQuery(QueryBuilders.matchAllQuery())
//                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .addAggregation(AggregationBuilders
                        .terms("group").field("age").field("salary").field("team")
                        .subAggregation(AggregationBuilders.max("max_age").field("age"))
                        .subAggregation(AggregationBuilders.avg("avg_age").field("age"))
                )//根据age分组，默认返回10，size(0)也是10
                .get();

        Terms terms = searchResponse.getAggregations().get("group");
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for(Terms.Bucket bt : buckets)
        {
            Max max = bt.getAggregations().get("max_age");
            Avg avg = bt.getAggregations().get("avg_age");
            System.out.println("key: " + bt.getKey() + ", count: " + bt.getDocCount()
                    + ", max: " + max.getValue() + ", avg: " + avg.getValue());
        }
    }

    /**
     * 分组聚合查询Demo，同时获得聚合信息和源信息
     * 本次查询的场景类似于如下SQL
     *  SELECT team Key, count(age) count, Max(age) max, AVG(age) avg
     *      FROM player_count
     *      WHERE team = 'war' AND salary > 500
     *      GROUP BY team
     *      ORDER BY count DESC
     *
     * 有所不同的是，bucket中除了搜索到的聚合信息外，还可以同时获得符合条件的源信息(分组状态)
     * @Title: searchTest2
     * @author liuqitian
     * @date 2018年6月20日
     * @return void
     */
    @Test
    public void searchTest2() {
        SearchResponse searchResponse = client.prepareSearch("player_count")
                .setQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("team", "war"))
                        .must(QueryBuilders.rangeQuery("salary").gt(500)))
                .addAggregation(AggregationBuilders
                        .terms("group").field("salary")
                        .subAggregation(AggregationBuilders.max("max_age").field("age"))
                        .subAggregation(AggregationBuilders.avg("avg_age").field("age"))
                        .subAggregation(AggregationBuilders.topHits("info"))
                        .order(Terms.Order.count(false))
                )
                .get();

        //获得bucket
        Terms terms = searchResponse.getAggregations().get("group");
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for(Terms.Bucket bt : buckets)
        {
            //获得本组源信息
            TopHits topHits = bt.getAggregations().get("info");
            //聚合函数需要逐一赋值给对应的类，否则无法调用对应的.getValue()方法
            Max max = bt.getAggregations().get("max_age");
            Avg avg = bt.getAggregations().get("avg_age");
            //输出聚合信息
            System.out.println("key: " + bt.getKey() + ", count: " + bt.getDocCount()
                    + ", max: " + max.getValue() + ", avg: " + avg.getValue());
            //循环输出源信息
            for (SearchHit hit : topHits.getHits().getHits()) {
                logger.info(" -> id [{}], _source [{}]", hit.getId(), hit.getSourceAsString());
            }
        }
    }

}
