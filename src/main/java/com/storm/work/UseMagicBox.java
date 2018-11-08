package com.storm.work;

import com.storm.proj.MagicBox;
import com.storm.work.impl.MagicBoxImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


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

}
