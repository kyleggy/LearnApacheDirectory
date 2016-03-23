package com.hpe.maas.ldap.test;

import com.hpe.maas.ldap.client.AddUser;
import org.junit.Test;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class TestAddUser {

    @Test
    public void testAddUser() {
        AddUser addUser = new AddUser();
        addUser.execute();
    }
}
