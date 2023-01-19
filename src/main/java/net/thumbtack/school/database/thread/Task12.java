package net.thumbtack.school.database.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task12 {
    public static void main(String[] args) {
        ConcurrentHashMapCustom map = new ConcurrentHashMapCustom();

        for(int i = 0; i < 3; i++){
            new PutCurrentMap(map, i, Thread.currentThread().getName()).start();
            new GetCurrentMap(map, i).start();
            new GetCurrentMap(map, i).start();
            new GetCurrentMap(map, i).start();
        }
    }
}

class ConcurrentHashMapCustom{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Integer, String> map = new HashMap<>();

    public void put(Integer key, String value){
        lock.writeLock().lock();
        try {
            map.put(key, value);
            System.out.println("  Map put: " + key + " key," + value + " value");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String get(Integer key){
        lock.readLock().lock();
        try {
            String value = map.get(key);
            System.out.println("Map get: " + key + " key," + value + " value");
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}

class PutCurrentMap extends Thread{
    private final Integer key;
    private final String value;
    private final ConcurrentHashMapCustom customMap;

    public PutCurrentMap(ConcurrentHashMapCustom customMap, Integer key, String value){
        this.customMap = customMap;
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        customMap.put(key, value);
    }
}

class GetCurrentMap extends Thread{
    private final Integer key;
    private final ConcurrentHashMapCustom customMap;

    public GetCurrentMap(ConcurrentHashMapCustom customMap, Integer key){
        this.customMap = customMap;
        this.key = key;
    }

    @Override
    public void run() {
        customMap.get(key);
    }
}
