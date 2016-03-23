package com.hpe.maas.ldap.test;

import com.hpe.maas.ldap.generator.ADLdapConnector;
import com.hpe.maas.ldap.generator.LdapConnector;
import org.junit.Test;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class TestSearchPaging {

    @Test
    public void testNormalSearchPaging() {
        LdapConnector connector = new ADLdapConnector();
        connector.setLdapConnection("16.186.78.139", 389,"cn=admin, cn=Users,dc=alex,dc=com", "1Qaz2wsx3edc");
        connector.pageSearch();

    }

}
