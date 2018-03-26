package top.huhuiyu.p101.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TestInfo implements Serializable {
  private static final long serialVersionUID = -1773103987373103238L;

  private int tid;
  private BigDecimal decTest;
  private String strTest;
  private Date dateTest;
  
  public TestInfo() {
  }

  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }

  public BigDecimal getDecTest() {
    return decTest;
  }

  public void setDecTest(BigDecimal decTest) {
    this.decTest = decTest;
  }

  public String getStrTest() {
    return strTest;
  }

  public void setStrTest(String strTest) {
    this.strTest = strTest;
  }

  public Date getDateTest() {
    return dateTest;
  }

  public void setDateTest(Date dateTest) {
    this.dateTest = dateTest;
  }

  @Override
  public String toString() {
    return "TestInfo [tid=" + tid + ", decTest=" + decTest + ", strTest=" + strTest + ", dateTest=" + dateTest + "]";
  }
  
}
