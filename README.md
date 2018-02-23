## 前言
本项目来自于 [https://github.com/4onni/elasticsearch-analysis-ansj]
- 对落后的依赖进行了升级，并提高了一些性能。

#### 请注意：此版本只支持Java8以上(lamba)、redis 2.7以上、ElasticSearch 2.1以上

#### 如果ES版本较低请使用原版！

## 版本对应

| plugin        |     elasticsearch|
| --------      |       -----:  | 
| 1.0.0Release  |     0.90.2    |
| master        |     1.0.0     |


==========

## 插件安装

进入Elasticsearch目录运行如下命令 

````
./bin/plugin -u http://maven.ansj.org/org/ansj/elasticsearch-analysis-ansj/1.x.1/elasticsearch-analysis-ansj-1.x.1-release.zip -i ansj
````

##下载安装

进入[http://maven.ansj.org/org/ansj/elasticsearch-analysis-ansj/](http://maven.ansj.org/org/ansj/elasticsearch-analysis-ansj/) 直接下 载zip包解压到plugin目录下



## 分词文件配置:


##### 简单配置:

```yaml
################################## ANSJ PLUG CONFIG ################################
index:
   analysis:
     analyzer:
     	index_ansj:
     		type: ansj_index
     	query_ansj:
     		type: ansj_query
     		
index.analysis.analyzer.default.type: ansj_index
```


##### 高级配置:
```yaml
################################## ANSJ PLUG CONFIG ################################
index:
   analysis:
     analyzer:
       index_ansj:
           alias: [ansj_index_analyzer]
           type: ansj_index
           is_name: false
           redis:
               pool:
                   maxactive: 20
                   maxidle: 10
                   maxwait: 100
                   testonborrow: true
               ip: master.redis.yao.com:6379
               channel: ansj_term
       query_ansj:
           alias: [ansj_query_analyzer]
           type: ansj_query
           is_name: false
           redis:
               pool:
                   maxactive: 20
                   maxidle: 10
                   maxwait: 100
                   testonborrow: true
               ip: master.redis.yao.com:6379
               channel: ansj_term
       customer_ansj_index:
           tokenizer: index_ansj_token
           filter: [sysfilter]
       customer_ansj_query:
           tokenizer: query_ansj_token
           filter: [sysfilter]
     tokenizer:
        index_ansj_token:
            type: ansj_index_token
            is_name: false
            is_num: false
            is_quantifier: false
            redis:
                pool:
                    maxactive: 20
                    maxidle: 10
                    maxwait: 100
                    testonborrow: true
                ip: master.redis.yao.com:6379
                channel: ansj_term
        query_ansj_token:
            type: ansj_query_token
            is_name: false
            is_num: false
            is_quantifier: false
            redis:
                pool:
                    maxactive: 20
                    maxidle: 10
                    maxwait: 100
                    testonborrow: true
                ip: master.redis.yao.com:6379
                channel: ansj_term
      filter:
        sysfilter:
            type: synonym
            synonyms:
                - 片,颗 =>粒
```
