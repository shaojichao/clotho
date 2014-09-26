package com.runmit.clotho.management.security;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import com.runmit.clotho.core.domain.admin.Admin;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月26日
 */
@Service
public class LDAPValidation {
	
	private static final Logger log = LoggerFactory
            .getLogger(LDAPValidation.class);
	
	@Value("${ldap.host}")
	private String ldapHost;
	
	@SuppressWarnings("unchecked")
	public Admin validAdmin(String uid,String password) {
		Admin admin = new Admin();
		
		String loginDN = "uid="+uid+",ou=users,dc=runmit,dc=com";
		String searchBase = "ou=users,dc=runmit,dc=com";
		String searchFilter = "";

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int searchScope = LDAPConnection.SCOPE_SUB;

		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(LDAPConnection.LDAP_V3, loginDN, password.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase,
					searchScope, searchFilter, null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					log.error("LDAPValidation LDAPException 1 ",e);
					if (e.getResultCode() == LDAPException.LDAP_TIMEOUT
							|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
						break;
					} else {
						continue;
					}
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator<LDAPAttribute> allAttributes = attributeSet.iterator();
				while (allAttributes.hasNext()) {
					LDAPAttribute attribute = allAttributes.next();
					String attributeName = attribute.getName();
					Enumeration<String> allValues = attribute.getStringValues();  
                    if (null == allValues) {  
                        continue;  
                    }
                    while (allValues.hasMoreElements()) {  
                        String value = allValues.nextElement();  
                        if(attributeName.equalsIgnoreCase("uid")){
                        	admin.setUid(uid);
    					}else if(attributeName.equalsIgnoreCase("mail")){
    						admin.setMail(value);
    					}else if(attributeName.equalsIgnoreCase("displayName")){
    						admin.setName(value);
    					}
                    } 
				}
				if(null!=admin.getUid()&&admin.getUid().equalsIgnoreCase(uid)){
					return admin;
				}
			}
			return null;
		} catch (LDAPException e) {
			log.error("LDAPValidation LDAPException 2 ",e);
			return null;
		} catch (UnsupportedEncodingException e) {
			log.error("LDAPValidation UnsupportedEncodingException 3 ",e);
			return null;
		} finally {
			try {
				if (lc.isConnected()) {
					lc.disconnect();
				}
			} catch (Exception e) {
				log.error("LDAPValidation lc.disconnect exception 4 ",e);
			}
		}
	}
	
}