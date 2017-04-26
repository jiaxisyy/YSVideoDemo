package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceInfo {

    /**
     * endRow : 1
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"agentId":"11","supplierId":"11","onlineStatus":"ONLINE","latitude":"111","serverPort":11,"delFlag":"0","deviceId":"deviceId0002","password":"password0002","deviceHost":"11","deviceModelId":"model1","onlineTime":1,"id":"1","addr":"1111","longitude":"1111","createDate":1465294203000,"totalRunTime":11111,"userId":"111111","serverHost":"111","picPath":"11111","createBy":"11111","areaId":"111111","name":"111","devicePort":11,"manufactorId":"111","status":"1"}]
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * orderBy : null
     * pageNum : 1
     * pageSize : 20
     * pages : 1
     * prePage : 0
     * size : 1
     * startRow : 1
     * total : 1
     */

    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private int navigatePages;
    private int nextPage;
    private Object orderBy;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * agentId : 11
         * supplierId : 11
         * onlineStatus : ONLINE
         * latitude : 111
         * serverPort : 11
         * delFlag : 0
         * deviceId : deviceId0002
         * password : password0002
         * deviceHost : 11
         * deviceModelId : model1
         * onlineTime : 1
         * id : 1
         * addr : 1111
         * longitude : 1111
         * createDate : 1465294203000
         * totalRunTime : 11111
         * userId : 111111
         * serverHost : 111
         * picPath : 11111
         * createBy : 11111
         * areaId : 111111
         * name : 111
         * devicePort : 11
         * manufactorId : 111
         * status : 1
         */

        private String agentId;
        private String supplierId;
        private String onlineStatus;
        private String latitude;
        private int serverPort;
        private String delFlag;
        private String deviceId;
        private String password;
        private String deviceHost;
        private String deviceModelId;
        private int onlineTime;
        private String id;
        private String addr;
        private String longitude;
        private String createDate;
        private int totalRunTime;
        private String userId;
        private String serverHost;
        private String picPath;
        private String createBy;
        private String areaId;
        private String name;
        private int devicePort;
        private String manufactorId;
        private String status;

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getServerPort() {
            return serverPort;
        }

        public void setServerPort(int serverPort) {
            this.serverPort = serverPort;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDeviceHost() {
            return deviceHost;
        }

        public void setDeviceHost(String deviceHost) {
            this.deviceHost = deviceHost;
        }

        public String getDeviceModelId() {
            return deviceModelId;
        }

        public void setDeviceModelId(String deviceModelId) {
            this.deviceModelId = deviceModelId;
        }

        public int getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(int onlineTime) {
            this.onlineTime = onlineTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getTotalRunTime() {
            return totalRunTime;
        }

        public void setTotalRunTime(int totalRunTime) {
            this.totalRunTime = totalRunTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getServerHost() {
            return serverHost;
        }

        public void setServerHost(String serverHost) {
            this.serverHost = serverHost;
        }

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDevicePort() {
            return devicePort;
        }

        public void setDevicePort(int devicePort) {
            this.devicePort = devicePort;
        }

        public String getManufactorId() {
            return manufactorId;
        }

        public void setManufactorId(String manufactorId) {
            this.manufactorId = manufactorId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
