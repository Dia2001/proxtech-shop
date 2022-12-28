package com.proxtechshop.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Role;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.RoleRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.MemberService;
import com.proxtechshop.utils.Utils;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private UserRepository repo;
	@Autowired
	private RoleRepository roleRepo;
	@Override
	public List<User> Search(String search) {
		
		return null;
	}
	@Override
	public boolean Remove(String id) {
		User user=repo.getReferenceById(id);
		try {
			repo.delete(user);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean UpdateMember(User user) {
		try {
			if(user.getId()==null&&repo.getByUsername(user.getUsername())!=null)
				return false;
			if(user.getCreatedDate()==null)
				user.setCreatedDate(new Date());
			//default: 12345
			if(user.getPassword()==null)
				user.setPassword("$2a$12$fe7oWUACUIy3COUQBLu5FeGjH9cLdt/5boBUZ74meepXWJxj8WuWy");
			repo.save(user);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	@Override
	public boolean ChangePass() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> LoadMembers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public Page<User> paginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		List<User> users=this.repo.findAll();
		//Eliminate users has customer role
		filterRoleCustomer(users);
		
		@SuppressWarnings("unchecked")
		Page<User> convertToPage=(Page<User>) Utils.toPage(users, pageable);
		return convertToPage;
	}
	private void filterRoleCustomer(List<User> users)
	{
		Role role=roleRepo.getReferenceById("ROLE_CUSTOMER");
		for(int i=0;i<users.size();i++)
		{
			if(users.get(i).getRoles().contains(role))
			{
				users.remove(i);
			}
		}
	}
	
	@Override
	public User GetUserById(String id) {
		
		return repo.getReferenceById(id);
	}
	@Override
	public Page<User> FilterAndPaginated(String search, int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		/* Page<User> findByName=repo.findByUsernameContains(search, pageable); */
		List<User> searchName=repo.findByUsernameContains(search);
		List<User> searchRole=filterByRole(repo.findAll(), search);
		//combine two result which search
		List<User> searchUsers=Stream.concat(searchName.stream(), searchRole.stream()).toList();
		@SuppressWarnings("unchecked")
		Page<User> pageUsers=(Page<User>)Utils.toPage(searchUsers, pageable);
		return pageUsers;
	}
	
	private List<User> filterByRole(List<User> users,String search)
	{
		List<User> userStore = new ArrayList<>();
		for(int i=0;i<users.size();i++) {
			for (Role role : users.get(i).getRoles()) {
				if(role.getRoleName().contains(search))
				{
					userStore.add(users.get(i));
				}
			}
		}
		return userStore;
	}
	
	

}
