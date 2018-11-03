package com.pwz.filter.e;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/10/1 16:27
 **/
public class FilterChain {

    private Filter[] filters = new Filter[3];
    {
        filters[0] = new Filter1();
        filters[1] = new Filter2();
        filters[2] = new Filter4();

    }


    /**
     * The int which is used to maintain the current position
     * in the filter chain.
     */
    private int pos = 0;


    /**
     * The int which gives the current number of filters in the chain.
     */
    private int n = 0;

    public void doFilter(){
        n=filters.length;
        if (pos < n) {
            Filter filter = filters[pos++];
            filter.filter(this);
        }
    }

}
