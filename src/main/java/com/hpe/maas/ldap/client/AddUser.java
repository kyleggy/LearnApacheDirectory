package com.hpe.maas.ldap.client;

import com.hpe.maas.ldap.datamodel.LdapUser;
import com.hpe.maas.ldap.generator.ADLdapConnector;
import com.hpe.maas.ldap.generator.LdapConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class AddUser {

    List<LdapUser> ldapUserList = new ArrayList<LdapUser>();

    public void prepareData(int index) {
        LdapUser userJuicy= new LdapUser();
        userJuicy.setCn("scottss" + index);
        userJuicy.setSn("Guss" + index);
        userJuicy.setUid("gyjddd" + index);
        userJuicy.setDn("cn=" + userJuicy.getCn()+ ",OU=maas,DC=alex,DC=local");
        userJuicy.setC("CN");
        ldapUserList.add(userJuicy);
    }

    public void execute() {
        LdapConnector connector = new ADLdapConnector();
        connector.setLdapConnection("16.186.78.135", 389,"CN=admin,CN=Users,DC=alex,DC=local", "1Qaz2wsx3edc");
        for (int i = 0; i < 1; i ++) {
            prepareData(i);
        }
        connector.addEntries(ldapUserList);
        connector.disConnectLdap();
    }
}
