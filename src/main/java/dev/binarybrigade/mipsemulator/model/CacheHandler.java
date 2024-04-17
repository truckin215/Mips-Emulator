package dev.binarybrigade.mipsemulator.model;

public class CacheHandler {
    public static CacheList L1 = new CacheList(2);
    public static CacheList L2 = new CacheList(4);
    public static CacheList L3 = new CacheList(8);

    public void insertIntoCache(int data) {
        // look for hit in L1, then L2, then L3
        // if a hit occurs, do nothing
        // if a miss occurs, load the data out of L3, from L2 to L3, from L1 to L2, then insert new data into L1
    }
}
