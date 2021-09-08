package com.abolkog.springboot.tut.chat;

public class Data
{

    private long id;
    private double open;
    private double close;
    private double low;
    private double high;
    private double amount;
    private double vol;
    private long count;
    
    public Data() {
        
    }
    
    public Data(long id, double open, double close, double low, double high, double amount, double vol, long count)
    {
        super();
        this.id = id;
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.amount = amount;
        this.vol = vol;
        this.count = count;
    }
    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public double getOpen()
    {
        return open;
    }
    public void setOpen(double open)
    {
        this.open = open;
    }
    public double getClose()
    {
        return close;
    }
    public void setClose(double close)
    {
        this.close = close;
    }
    public double getLow()
    {
        return low;
    }
    public void setLow(double low)
    {
        this.low = low;
    }
    public double getHigh()
    {
        return high;
    }
    public void setHigh(double high)
    {
        this.high = high;
    }
    public double getAmount()
    {
        return amount;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    public double getVol()
    {
        return vol;
    }
    public void setVol(double vol)
    {
        this.vol = vol;
    }
    public long getCount()
    {
        return count;
    }
    public void setCount(long count)
    {
        this.count = count;
    }
    
    
}
