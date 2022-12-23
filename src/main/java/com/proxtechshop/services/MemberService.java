package com.proxtechshop.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.proxtechshop.entities.User;

public interface MemberService {
	List<User> Search(String search);
	boolean Remove(String id);	
	boolean UpdateMember(User user);
	boolean ChangePass();
	User GetUserById(String id);
	List<User> LoadMembers();
	Page<User> paginated(int pageNo, int pageSize, String sortField, String sortDirection);
	Page<User> FilterAndPaginated(String search,int pageNo, int pageSize, String sortField, String sortDirection);
}
