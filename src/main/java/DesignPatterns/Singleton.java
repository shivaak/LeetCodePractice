package DesignPatterns;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    Note that the volatile keyword is used to ensure that changes to the instance variable are visible across threads.
    The double-checked locking idiom is a widely-used technique to create singleton objects in a thread-safe manner.
    However, it should be used with caution as incorrect implementation can lead to subtle bugs and performance issues.
     */
    private static volatile Singleton instance;

    private Singleton(){
        if(instance!=null){
            throw new IllegalStateException("Already initialized");
        }
    }

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class) {
                if(instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private Singleton readResolve() {
        return instance;
    }
}
