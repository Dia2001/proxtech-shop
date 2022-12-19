package com.proxtechshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private UserRepository repo;

	@Override
	public List<User> Search(String search) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean Remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean Modify(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean AddMember() {
		// TODO Auto-generated method stub
		return false;
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
		//remove all user have customer roles or empty roles
		//hasnt coded yet
		return this.repo.findAll(pageable);
	}
	@Override
	public User GetUserById(String id) {
		
		return repo.getReferenceById(id);
	}

}
