package com.hpe.maas.ldap.generator;

import com.hpe.maas.ldap.datamodel.LdapUser;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.message.controls.PagedResults;
import org.apache.directory.api.ldap.model.message.controls.PagedResultsImpl;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lizhaok on 2016/3/22.
 */
public class ADLdapConnector implements LdapConnector {

    private LdapConnection connection;

    @Override
    public LdapConnection setLdapConnection(String ip, int port, String userName, String password){
        connection = new LdapNetworkConnection(ip, port);
        connection.setTimeOut(6000);
        try {
            connection.bind(userName, password);
        } catch (LdapException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void disConnectLdap() {
        try {
            connection.unBind();
        } catch (LdapException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEntries(List<LdapUser> ldapUsers) {
        int length = ldapUsers.size();
        for (int i = 0; i < length; i ++ ){
            LdapUser ldapUser = ldapUsers.get(i);
            addEntry(ldapUser.getDn(), ldapUser.getObjectTop(), ldapUser.getObjectClassinetOrgPerson(), ldapUser.getObjectClassPerson()
                    , "cn", ldapUser.getCn(), "sn", ldapUser.getSn(), "uid", ldapUser.getUid(), "c", ldapUser.getC());
        }
    }

    @Override
    public void pageSearch() {
        byte[] cookie = null;
        SearchRequest searchRequest = new SearchRequestImpl();

        try {
            searchRequest.setBase(new Dn("OU=maas,DC=alex,DC=com"));
            searchRequest.setFilter("(&(objectClass=person)(objectClass=user))");
            //searchRequest.setMessageId(1);
            searchRequest.setSizeLimit(100);
            PagedResults pagedResults = new PagedResultsImpl();
            pagedResults.setSize(2);
            pagedResults.setCritical(true);
            searchRequest.addControl(pagedResults);
            searchRequest.setScope(SearchScope.ONELEVEL);
            String[] searchAttributes = new String[2];
            searchAttributes[0] = "mail";
            searchAttributes[1] = "sAMAccountName";

            searchRequest.addAttributes(searchAttributes);
            do {
                SearchCursor cursor = connection.search(searchRequest);
                while (cursor.next()) {
                    Response response = cursor.get();
                    if (response instanceof SearchResultEntry) {
                        Entry entry = ((SearchResultEntry)response).getEntry();
                        Collection<Attribute> attributeCollection = entry.getAttributes();
                        Iterator<Attribute> attributeIterator = attributeCollection.iterator();
                        while (attributeIterator.hasNext()) {
                            System.out.println(attributeIterator.next());
                        }
                    }
                }
                PagedResults tempPagedResults = (PagedResults) cursor.getSearchResultDone().getControl(pagedResults.getOid());
                cookie = tempPagedResults.getCookie();
                pagedResults.setCookie(cookie);
                searchRequest.addControl(pagedResults);
            } while (cookie != null);

        } catch (LdapException e) {
            e.printStackTrace();
        } catch (CursorException e) {
            e.printStackTrace();
        }


    }

    private void addEntry(String dn, Object... objects) {
        try {
            Entry entry = new DefaultEntry(dn, objects);
            connection.add(entry);
        } catch (LdapException e) {
            e.printStackTrace();
        }
    }



}
