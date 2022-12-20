package com.proxtechshop.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proxtechshop.common.Validate;
import com.proxtechshop.entities.Product;
import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.repositories.CustomProductRepository;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Product> getFilter(int pageSize, ProductFilter filter) {
		Query q = getQuery(filter);
		if (filter.getPage() < 1) {
			q.setFirstResult(0);
		} else {
			q.setFirstResult((filter.getPage() - 1) * pageSize);
		}
		q.setMaxResults(pageSize);
		
		return q.getResultList();
	}

	@Override
	public int getTotalRecordFilter(int pageSize, ProductFilter filter) {
		return getQuery(filter).getResultList().size();
	}
	
	private Query getQuery(ProductFilter filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		
		List<Predicate> predList = new ArrayList<>();
		
		if (Validate.checkStringNotEmptyOrNull(filter.getSearch())) {
			predList.add(builder.and(builder.like(root.get("name"),"%" + filter.getSearch() + "%")));
		}

		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		query.where(predArray);
		return em.createQuery(query);
	}
}
