package com.hpe.maas.ldap;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;


/**
 * Created by lizhaok on 2016/2/25.
 */
public class LearnApacheDirectory {

    public void configureConnectLdap() {
        LdapConnectionConfig config = new LdapConnectionConfig();
        config.setLdapHost("localhost");
        config.setLdapPort(10388);
        config.setName("uid=admin, ou=system");
        config.setCredentials("secret");

        DefaultPoolableLdapConnectionFactory defaultPoolableLdapConnectionFactory = new DefaultPoolableLdapConnectionFactory(config);
        LdapConnectionPool pool = new LdapConnectionPool( defaultPoolableLdapConnectionFactory );
        pool.setTestOnBorrow( true );
        try {
            pool.getConnection().bind();
        } catch (LdapException e) {
            e.printStackTrace();
        }


    }

    public void connectionLdap() {
        LdapConnection connection = new LdapNetworkConnection( "localhost", 10388 );
        // 0 for opening connection for ever, by default is 30 seconds
        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("uid=admin,ou=system", "secret");
            EntryCursor cursor = connection.search("ou=system", "(objectclass=*)", SearchScope.ONELEVEL, "*");
            try {
                while (cursor.next()) {
                    Entry entry = cursor.get();
                    Collection<Attribute> attributeCollection = entry.getAttributes();
                    Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                    while (attributeIterator.hasNext()) {
                        System.out.println(attributeIterator.next());
                    }
                }
            } catch (CursorException e) {
                e.printStackTrace();
            }
            connection.unBind();
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (LdapException e) {
            e.printStackTrace();
        }
    };








}
