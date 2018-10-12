package org.zj.cache;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/12 21:17
 * @Description:
 */
public class Content {
    public static void main(String[] args) {
        CacheChain cacheChain=new CacheChain();
        cacheChain.put("a",new Student("zhangjun","zhangjun249",19));
        cacheChain.put("b",new Student("asdf","zhangjun249",19));
        cacheChain.put("c",new Student("zhanasdgjun","zhangjun249",19));
        cacheChain.put("d",new Student("asdf","zhangjun249",19));
        cacheChain.put("e",new Student("fffffff","zhangjun249",19));
        cacheChain.put("f",new Student("aaaaaaaaaaa","zhangjun249",19));


        System.out.println(cacheChain.get("a")+"aaaaaaaaaaa");
        cacheChain.cleanNode(3000,2);



    }
}
