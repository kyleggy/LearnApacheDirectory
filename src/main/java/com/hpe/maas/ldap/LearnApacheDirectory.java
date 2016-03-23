package com.hpe.maas.ldap;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.directory.api.ldap.extras.controls.ad.AdShowDeleted;
import org.apache.directory.api.ldap.extras.controls.ad.AdShowDeletedImpl;
import org.apache.directory.api.ldap.extras.controls.ad_impl.AdShowDeletedFactory;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.*;


import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.apache.directory.ldap.client.api.search.FilterBuilder.*;


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
            //pool.getConnection().bind();
            LdapConnection connection = pool.getConnection();
            connection.bind();
            EntryCursor cursor = connection.search("o=mojo", "(objectclass=*)", SearchScope.ONELEVEL, "*");
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


    }

    public void connectionLdap() {
        LdapConnection connection = new LdapNetworkConnection( "localhost", 10388 );
        // 0 for opening connection for ever, by default is 30 seconds
        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("uid=admin,ou=system", "secret");
            EntryCursor cursor = connection.search("o=mojo", "(objectclass=*)", SearchScope.ONELEVEL, "*");
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


    public void connectADWin2008Ldap() {
        LdapConnection connection = new LdapNetworkConnection( "16.186.78.135", 389 );

        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("cn=admin, cn=Users,dc=alex,dc=local", "1Qaz2wsx3edc");
            //EntryCursor cursor = connection.search("ou=maas, dc=alex,dc=local", "(objectclass=*)", SearchScope.ONELEVEL, "*");
            EntryCursor cursor = connection.search(" cn=Users, dc=alex,dc=local", "(objectclass=*)", SearchScope.ONELEVEL, "*");
            try {
                while (cursor.next()) {
                    Entry entry = cursor.get();
                    Collection<Attribute> attributeCollection = entry.getAttributes();
                    Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                    while (attributeIterator.hasNext()) {
                        System.out.println(attributeIterator.next());
                    }
                }
                cursor.close();
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

    public void searchDN() {
        LdapConnection connection = new LdapNetworkConnection( "16.186.78.135", 389 );

        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("cn=admin, cn=Users,dc=alex,dc=local", "1Qaz2wsx3edc");

            Dn maasDN = new Dn("OU=maas,DC=alex,DC=local");


            //EntryCursor cursor = connection.search(maasDN, "(objectclass=*)", SearchScope.ONELEVEL, "name");
            //EntryCursor cursor = connection.search(maasDN, "(objectclass=person *| displayName=\"joezhou\")", SearchScope.ONELEVEL, "userPrincipalName");
            String filter =
                            and(
                                    equal( "objectClass", "person" ),
                                    equal( "sn", "zhou" ), greaterThanOrEqual("whenCreated", "20150301071156.000Z"))
                            .toString();


            EntryCursor cursor = connection.search(maasDN, filter, SearchScope.ONELEVEL, "userPrincipalName");

            try {
                while (cursor.next()) {
                    Entry entry = cursor.get();
                    Collection<Attribute> attributeCollection = entry.getAttributes();
                    Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                    while (attributeIterator.hasNext()) {
                        System.out.println(attributeIterator.next());
                    }
                }
                cursor.close();
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

    }

    public void searchRequest() {
        LdapConnection connection = new LdapNetworkConnection( "16.186.78.135", 389 );
        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("cn=admin, cn=Users,dc=alex,dc=local", "1Qaz2wsx3edc");

            Dn maasDN = new Dn("OU=maas,DC=alex,DC=local");
            String filter =
                    and(
                            equal( "objectClass", "person" ),
                            equal( "sn", "zhou" ), greaterThanOrEqual("whenCreated", "20150301071156.000Z"))
                            .toString();
            SearchRequest req = new SearchRequestImpl();
            req.setBase(maasDN);
            req.setScope(SearchScope.ONELEVEL);
            req.setFilter(filter);
            //req.addAttributes("userPrincipalName");
            req.addAttributes("*");
            //req.addAttributes("objectClass");
            SearchCursor searchCursor = connection.search(req);

            try {
                while (searchCursor.next()) {
                    Response response = searchCursor.get();
                    if (response instanceof SearchResultEntry) {
                        Entry entry = ((SearchResultEntry)response).getEntry();
                        Collection<Attribute> attributeCollection = entry.getAttributes();
                        Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                        while (attributeIterator.hasNext()) {
                            System.out.println(attributeIterator.next());
                        }
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
    }

    public void searchDeleteAndDisableRequest() {
        LdapConnection connection = new LdapNetworkConnection( "16.186.78.139", 389 );
        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("cn=admin, cn=Users,dc=alex,dc=com", "1Qaz2wsx3edc");

            Dn maasDN = new Dn("OU=maas,DC=alex,DC=com");

            SearchRequest req = new SearchRequestImpl();
           // req.setBase(maasDN);

            Dn hDN = new Dn("DC=alex,DC=com");
            req.setBase(hDN);
            req.setScope(SearchScope.SUBTREE);
            req.setFilter("(objectClass=*)");
            //req.addAttributes("userPrincipalName");
            req.addAttributes("name");

            //LDAP_SERVER_SHOW_DELETED_OID
            //AdShowDeletedFactory adShowDeletedFactory = new
            AdShowDeleted adShowDeleted = new AdShowDeletedImpl();
            adShowDeleted.setCritical(true);
            req.addControl(adShowDeleted);

            //req.addAttributes("objectClass");
            SearchCursor searchCursor = connection.search(req);

            try {
                while (searchCursor.next()) {

                    Response response = searchCursor.get();
                    if (response instanceof SearchResultEntry) {
                        Entry entry = ((SearchResultEntry)response).getEntry();
                        Collection<Attribute> attributeCollection = entry.getAttributes();
                        Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                        while (attributeIterator.hasNext()) {
                            System.out.println(attributeIterator.next());
                        }
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
    }
    private static final String DEFAULT_PERSON_ATTRIBUTE = "sn, givenName, mail, userPrincipalName,co,homePhone,telephoneNumber";
    public void testSearchAD2012() {
        LdapConnection connection = new LdapNetworkConnection( "16.186.78.139", 389 );
        connection.setTimeOut(0);
        try {

            //connection.bind( "o=mojo", "secret" );
            connection.bind("cn=admin, cn=Users,dc=alex,dc=com", "1Qaz2wsx3edc");

            Dn maasDN = new Dn("OU=maas,DC=alex,DC=com");
            String filter =
                    and(
                            equal( "objectClass", "person" ),
                            equal( "sn", "zhou" ), greaterThanOrEqual("whenCreated", "20150301071156.000Z"))
                            .toString();
            SearchRequest req = new SearchRequestImpl();
            req.setBase(maasDN);
            req.setScope(SearchScope.ONELEVEL);
            req.setFilter("(&(objectClass=person)(objectClass=user))");
            //req.addAttributes("userPrincipalName");
            String[] personAttributes =  DEFAULT_PERSON_ATTRIBUTE.split(",");

            req.addAttributes(personAttributes);
            //req.addAttributes("objectClass");
            SearchCursor searchCursor = connection.search(req);

            try {
                while (searchCursor.next()) {
                    Response response = searchCursor.get();
                    if (response instanceof SearchResultEntry) {
                        Entry entry = ((SearchResultEntry)response).getEntry();
                        Collection<Attribute> attributeCollection = entry.getAttributes();
                        Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                        while (attributeIterator.hasNext()) {
                            System.out.println(attributeIterator.next());
                        }
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

    }

    }
