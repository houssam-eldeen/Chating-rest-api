package com.abolkog.springboot.tut.chat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chart
{

  private String ch;
  private String status;
  private long ts;
  
  
  private List<Data> data;

  public Chart() {
      
  }
  
public Chart(String ch, String status, long ts, List<Data> data)
{
    super();
    this.ch = ch;
    this.status = status;
    this.ts = ts;
    this.data = data;
}

public String getCh()
{
    return ch;
}

public void setCh(String ch)
{
    this.ch = ch;
}

public String getStatus()
{
    return status;
}

public void setStatus(String status)
{
    this.status = status;
}

public long getTs()
{
    return ts;
}

public void setTs(long ts)
{
    this.ts = ts;
}

@JsonProperty("data")
public List<Data> getDataList()
{
    return data;
}

public void setDataList(List<Data> dataList)
{
    this.data = dataList;
}
  
  
  
  
}
