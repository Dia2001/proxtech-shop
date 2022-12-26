package com.proxtechshop.repositoryimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proxtechshop.entities.Order;
import com.proxtechshop.repositories.CustomOrderRepository;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Order> listOrderInStartAndEndDate(Date start, Date end) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		List<Predicate> predList = new ArrayList<>();
		
//		predList.add(builder.or(builder.gt(root.get("createdDate"), start),
//				builder.equal(root.get("createdDate"), start)));
		predList.add(builder.between(builder.currentDate(), start, end));

		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		query.where(predArray);
		return em.createQuery(query).getResultList();
	}
}
