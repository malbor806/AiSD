/**
 * Created by malbor806 on 19.03.2017.
 */
public class Count {
    private long comparisionWithKey;
    private long keySwap;

    public Count() {
        comparisionWithKey = 0;
        keySwap = 0;
    }

    public void setComparisionWithKey(long i) {
        this.comparisionWithKey = i;
    }

    public void setKeySwap(long i) {
        this.keySwap = i;
    }

    public long getComparisionWithKey() {
        return comparisionWithKey;
    }

    public long getKeySwap() {
        return keySwap;
    }
}
