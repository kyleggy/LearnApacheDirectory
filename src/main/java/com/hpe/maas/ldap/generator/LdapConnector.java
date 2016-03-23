package com.hpe.maas.ldap.generator;

import com.hpe.maas.ldap.datamodel.LdapUser;
import org.apache.directory.ldap.client.api.LdapConnection;

import java.util.List;

/**
 * Created by lizhaok on 2016/3/22.
 */
public interface LdapConnector {
    public LdapConnection setLdapConnection(String ip, int port, String userName, String password);

    public void disConnectLdap();

    public void addEntries(List<LdapUser> ldapUsers);

    public void pageSearch();

}
