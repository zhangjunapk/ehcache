package org.zj.cache;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/12 20:49
 * @Description: 缓存节点
 */
public class CacheNode {
    private String key;
    private Object val;
    private CacheNode next;
    private CacheNode last;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public CacheNode getNext() {
        return next;
    }

    public void setNext(CacheNode next) {
        this.next = next;
    }

    public CacheNode getLast() {
        return last;
    }

    public void setLast(CacheNode last) {
        this.last = last;
    }

    public CacheNode() {
    }

    public CacheNode(String key) {
        this.key = key;
    }

    public CacheNode(String key, Object val, CacheNode next, CacheNode last) {
        this.key = key;
        this.val = val;
        this.next = next;
        this.last = last;
    }
}
