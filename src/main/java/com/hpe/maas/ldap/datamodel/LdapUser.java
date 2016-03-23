package com.hpe.maas.ldap.datamodel;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class LdapUser {
    String cn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    String sn;
    String uid;
    String dn;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    String c;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    String countryCode;

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    String co;

    public String getObjectTop() {
        return objectTop;
    }

    String objectTop = "objectClass:top";

    public String getObjectClassinetOrgPerson() {
        return objectClassinetOrgPerson;
    }

    public String getObjectClassPerson() {
        return objectClassPerson;
    }

    String objectClassinetOrgPerson = "objectClass:inetOrgPerson";
    String objectClassPerson =  "objectClass:Person";
}
