package com.hpe.maas.ldap.test;

import com.hpe.maas.ldap.LearnApacheDirectory;
import org.junit.Test;

/**
 * Created by lizhaok on 2016/2/25.
 */
public class TestLdap {
    LearnApacheDirectory learnApacheDirectory = new LearnApacheDirectory();


    @Test
    public void testConnection() {
        learnApacheDirectory.connectionLdap();
    }

    @Test
    public void testConfigureConnectLdap() {
        learnApacheDirectory.configureConnectLdap();
    }

    @Test
    public void testConnectionADWin2008() {
        learnApacheDirectory.connectADWin2008Ldap();
    }

    @Test
    public void testSearchDN() {
        learnApacheDirectory.searchDN();
    }

    @Test
    public void testSearchRequest() {
        learnApacheDirectory.searchRequest();
    }

    @Test
    public void testSearchDeleteAndDisableRequest() {
        learnApacheDirectory.searchDeleteAndDisableRequest();
    }

    @Test
    public void testSearchAD2012() {
        learnApacheDirectory.testSearchAD2012();
    }

}
