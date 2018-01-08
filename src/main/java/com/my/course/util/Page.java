package com.my.course.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/8.
 * 分页实体类
 */
public class Page<T> {
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    protected Integer pageNo = 1;
    protected Integer pageSize = 5;
    protected boolean autoCount = true;
    protected List<T> result = new ArrayList<T>();
    protected long totalCount = -1L;

    public Page() {
    }

    public Page(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if (getTotalPages() == 1L)
            return 1;
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1)
            this.pageNo = 1;
    }

    public Page<T> pageNo(Integer thePageNo) {
        setPageNo(thePageNo);
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Page<T> pageSize(Integer thePageSize) {
        setPageSize(thePageSize);
        return this;
    }

    public Integer getFirst() {
        return ((this.pageNo - 1) * this.pageSize + 1);
    }

    public boolean isAutoCount() {
        return this.autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public Page<T> autoCount(boolean theAutoCount) {
        setAutoCount(theAutoCount);
        return this;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPages() {
        if (this.totalCount < 0L) {
            return -1L;
        }
        if (this.totalCount == 0L) {
            return 1L;
        }
        long count = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize > 0L) {
            count += 1L;
        }
        return count;
    }

    public boolean isHasNext() {
        return (this.pageNo + 1 <= getTotalPages());
    }

    public Integer getNextPage() {
        if (isHasNext()) {
            return (this.pageNo + 1);
        }
        return this.pageNo;
    }

    public boolean isHasPre() {
        return (this.pageNo - 1 >= 1);
    }

    public Integer getPrePage() {
        if (isHasPre()) {
            return (this.pageNo - 1);
        }
        return this.pageNo;
    }
}
