package org.zj.cache;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/12 20:51
 * @Description:
 */
public class CacheChain {
    int size;
    private CacheNode headNode ;
    private CacheNode tailNode ;

    private long lastCleanTime = System.currentTimeMillis();

    public Object get(String key) {
        if(size==0){
            return null;
        }
        if (headNode.getKey().equals(key))
            return headNode.getVal();

        //遍历节点找到使用了这个key的
        for (CacheNode cacheNode = headNode; cacheNode != null; cacheNode = cacheNode.getNext()) {
            if (cacheNode.getKey().equals(key)) {
                //找到了就把这个节点放到头部，然后把结果返回给调用者
                switchToHead(cacheNode);
                return cacheNode.getVal();
            }
        }
        return null;
    }

    /**
     * 把这个节点放到头部
     *
     * @param cacheNode
     */
    private void switchToHead(CacheNode cacheNode) {
        if (cacheNode.getLast() != null) {
            CacheNode last = cacheNode.getLast();
            if (cacheNode.getNext() != null) {
                //处理他前面和后面的节点
                CacheNode next = cacheNode.getNext();
                next.setLast(last);
                last.setNext(next);
            }

            //直接和头部交换就行
            headNode.setLast(cacheNode);
            headNode = cacheNode;
            return;
        }
        //如果前面是空的，那说明你已经是头了，我就不管你
    }

    /**
     * 把指定键值对放到链表中
     *
     * @param key
     * @param val
     */
    public void put(String key, Object val) {

        if(headNode==null){
            CacheNode cacheNode=new CacheNode(key,val,tailNode,null);
            headNode=cacheNode;
            System.out.println("头部初始化");
            return;
        }
        if(tailNode==null){
            CacheNode cacheNode=new CacheNode(key,val,null,headNode);
            tailNode=cacheNode;
            System.out.println("尾部初始化");
            return;
        }


        //直接插入到尾部
        CacheNode cacheNode = new CacheNode();
        cacheNode.setKey(key);
        cacheNode.setVal(val);

        //放到头部的，搞错了
        headNode.setLast(cacheNode);
        cacheNode.setNext(headNode);
        headNode=cacheNode;

        size++;
        System.out.println("放进去了");
    }

    /**
     * 指定时间会检测节点数量，然后清理掉
     *
     * @param time
     * @param needCount
     */
    public void cleanNode(final int time, final int needCount) {
        //开启线程来清理节点
        new Thread(new Runnable() {
            public void run() {
                for (;;) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - lastCleanTime > time) {
                        //这里就说明需要进行清理了
                        doClean(tailNode, needCount);
                        lastCleanTime=System.currentTimeMillis();
                    }
                }
            }
        }).start();
    }

    /**
     * 清理工作
     *
     * @param cacheNode
     * @param needCount
     */
    private void doClean(CacheNode cacheNode, int needCount) {
        if (size <= needCount)
            return;

        //开始清理
        if (cacheNode.getLast() == null) {
            cacheNode = null;
            return;
        }
        cacheNode.getLast().setNext(null);
        cacheNode = null;
        System.out.println("----------清理掉一个");
        size--;

        if(cacheNode==null||cacheNode.getLast()==null)
            return;

        doClean(cacheNode.getLast(), needCount);
    }
}
