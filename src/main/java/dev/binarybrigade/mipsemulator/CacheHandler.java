package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.CacheList;

public class CacheHandler {
    public static CacheList L1 = new CacheList(2);
    public static CacheList L2 = new CacheList(4);
    public static CacheList L3 = new CacheList(8);

    private static int hitCount;
    private static int totalCount;

    public static void insertIntoCache(long data) {
        if (data == 0) {
            return;
        }
        // look for hit in L1, then L2, then L3
        // if a hit occurs, do nothing
        // if a miss occurs, load the data out of L3, from L2 to L3, from L1 to L2, then insert new data into L1
        int L1Index = (int) data % L1.getMaxIndex();
        int L2Index = (int) data % L2.getMaxIndex();
        // check if a miss occurs in all 3 levels of cache, if true, load the data out of L3, from L2 to L3, from L1 to L2, then insert new data into L1
        if (L1.miss(data)) {
            if (L2.miss(data)) {
                if (L3.miss(data)) {
                    // store data from L1, then load the new data into L1
                    long L1Temp = L1.getData(L1Index);
                    L1.loadData(data);
                    // store the data from L2, then load L1 data into L2
                    long L2Temp = L2.getData(L2Index);
                    L2.loadData(L1Temp);
                    // load the data from L2 into L3 and discard the data from L3
                    L3.loadData(L2Temp);
                }
                else { // L3 hit
                    hitCount++;
                }
            }
            else { // L2 hit
                hitCount++;
            }
        }
        else { // L1 hit
            hitCount++;
        }
        totalCount++;


    }

    public static double getHitRate() {
        if (totalCount == 0) {
            return 0;
        }
        return (double) hitCount / (double) totalCount;
    }
}
