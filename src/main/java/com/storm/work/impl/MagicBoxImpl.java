package com.storm.work.impl;

import com.storm.proj.MagicBox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MagicBoxImpl implements MagicBox {

    public void surprise() {
        // not now
    }

    public String magicWords() {
        return "HELLO";
    }
}
