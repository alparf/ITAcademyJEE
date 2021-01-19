package by.academy.pool;

import java.util.Hashtable;
import java.util.Map;

public abstract class AbstractPoll<T> {
    protected final long liveTime;
    protected final Map<T, Long> lock;
    protected final Map<T, Long> unLock;

    public AbstractPoll() {
        this.liveTime = 60_000;
        this.lock = new Hashtable<>();
        this.unLock = new Hashtable<>();
    }

    public T get() {
        synchronized (AbstractPoll.class) {
            long currentTime = System.currentTimeMillis();
            if (this.unLock.size() > 0) {
                for (T t : this.unLock.keySet()) {
                    if (currentTime - this.unLock.get(t) > this.liveTime) {
                        this.unLock.remove(t);
                        close(t);
                    } else {
                        if (this.validate(t)) {
                            this.unLock.remove(t);
                            this.lock.put(t, currentTime);
                            return t;
                        } else {
                            this.unLock.remove(t);
                            close(t);
                        }
                    }
                }
            }
        }
        T other = open();
        this.lock.put(other, System.currentTimeMillis());
        return other;
    }

    public void put(T t) {
        this.lock.remove(t);
        this.unLock.put(t, System.currentTimeMillis());
    }

    abstract T open();
    abstract void close(T t);
    abstract boolean validate(T t);
}
