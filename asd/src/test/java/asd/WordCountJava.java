package asd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCountJava {
    public static void main(String[] args) {
     
        SparkConf conf = new SparkConf();
        conf.setAppName("WordCountScala");
        //����master����
        conf.setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
 
        JavaRDD<String> rdd1 = sc.textFile("D://test.txt");
        //ѹ��
        JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                List<String> list = new ArrayList<String>();
                String[] arr = s.split(" ");
                for(String ss : arr){
                    list.add(ss) ;
                }
                return list.iterator() ;
            }
        });
        //ӳ��
        JavaPairRDD<String,Integer> rdd3 = rdd2.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });
        //�ۺ�
        JavaPairRDD<String,Integer> rdd4 = rdd3.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        //�ռ�,��ӡ���
        for(Object o : rdd4.collect()){
            System.out.println(o);
        }
    }
}
