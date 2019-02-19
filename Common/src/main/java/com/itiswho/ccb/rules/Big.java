package com.itiswho.ccb.rules;

public class Big implements Rule {
    @Override
    public boolean check() {
        return false;
    }

    @Override
    public int getVal() {
        return 0;
    }

    @Override
    public int getType() {
        return 1;
    }
}
