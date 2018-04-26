package com.emusic.event;

import java.util.Arrays;
import java.util.Collection;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.OrderStatusRepository;
import com.emusic.dao.PrivilegeRepository;
import com.emusic.dao.ProductDao;
import com.emusic.dao.RoleRepository;
import com.emusic.dao.UserDao;
import com.emusic.model.OrderStatus;
import com.emusic.model.Privilege;
import com.emusic.model.Product;
import com.emusic.model.Role;
import com.emusic.model.User;

@Component
public class InitialDataLoader implements
ApplicationListener<ContextRefreshedEvent> {
	boolean alreadySetup = false;
	
	@Autowired
	UserDao userDao;
	
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    @Autowired
    OrderStatusRepository orderStatusRepository;
	
    @Autowired
    ProductDao productDao;
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
       
        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        
        createStatusIfNotFound("ORDER STARTED");
        createStatusIfNotFound("PROCESSING");
        createStatusIfNotFound("SHIPPING");
        createStatusIfNotFound("DELIVERED");
        createStatusIfNotFound("CANCELED");
        
        String testEmail = "test@test.com";
        if(userDao.getUserByEmail(testEmail) == null) {
	        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
	        User user = new User();
	        user.setPassword("test");
	        user.setEmail(testEmail);
	        user.setRoles(Arrays.asList(adminRole));
	        userDao.updateUser(user);
        }
        alreadySetup = true;
        
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(productDao.getEntityManager());
        try {
			fullTextEntityManager.createIndexer(Product.class).startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
    
    @Transactional OrderStatus createStatusIfNotFound(String name) {
    	OrderStatus orderStatus = orderStatusRepository.findByName(name);
    	if(orderStatus == null) {
    		orderStatus = new OrderStatus(name);
    		orderStatusRepository.save(orderStatus);
    	}
    	return orderStatus;
    }
}
