package com.storm.work;

import com.storm.proj.MagicBox;
import com.storm.work.impl.MagicBoxImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class UseMagicBox {

    @Autowired
    MagicBox magicBox;
    //MagicBoxImpl magicBox;

    private long date = Date.parse("01-Jan-2018");

    public long whatTheDate() {
        return date;
    }

    public String doMagic() {
        return magicBox.magicWords();
    }

    public void anyMethod() {
        ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(1);

        service.scheduleAtFixedRate(() -> {
            int a = 1;
            int b = 1;
            int c = a + b;
            System.out.println(c);
            },
                1, 1, TimeUnit.MICROSECONDS);
    }
}
