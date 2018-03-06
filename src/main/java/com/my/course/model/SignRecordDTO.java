package com.my.course.model;

public class SignRecordDTO {
    private Integer pageNo;
    private Integer pageSize;
    private Integer signruleId;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSignruleId() {
        return signruleId;
    }

    public void setSignruleId(Integer signruleId) {
        this.signruleId = signruleId;
    }
}
