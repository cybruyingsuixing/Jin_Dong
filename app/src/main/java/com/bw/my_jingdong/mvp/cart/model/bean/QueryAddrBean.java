package com.bw.my_jingdong.mvp.cart.model.bean;

import java.util.List;

public class QueryAddrBean {

    /**
     * msg : 地址列表请求成功
     * code : 0
     * data : [{"addr":"eeeeeee","addrid":9995,"mobile":13156234563,"name":"ww","status":0,"uid":14789},{"addr":"333","addrid":9996,"mobile":13523566523,"name":"66666","status":0,"uid":14789},{"addr":"22","addrid":9997,"mobile":3333333,"name":"555","status":0,"uid":14789},{"addr":"22222","addrid":9999,"mobile":33333333,"name":"555","status":0,"uid":14789},{"addr":"5555555","addrid":10000,"mobile":22222222,"name":"5855","status":0,"uid":14789}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : eeeeeee
         * addrid : 9995
         * mobile : 13156234563
         * name : ww
         * status : 0
         * uid : 14789
         */

        private String addr;
        private int addrid;
        private String mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
