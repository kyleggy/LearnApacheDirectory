package com.hpe.maas.ldap.generator;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class LdapConfiguration {
    public String ip;
    public int port;
    public String userName;
    public String password;
    public String searchDNBase;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSearchDNBase() {
        return searchDNBase;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSearchDNBase(String searchDNBase) {
        this.searchDNBase = searchDNBase;
    }

}
