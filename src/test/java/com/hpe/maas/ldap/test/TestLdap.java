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

}
