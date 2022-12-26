package com.proxtechshop.repositoryimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proxtechshop.common.Validate;
import com.proxtechshop.entities.Category;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.repositories.CustomProductRepository;
import com.proxtechshop.repositories.ProductRepository;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private ProductRepository pr;

	@Override
	public List<Product> getFilter(int pageSize, ProductFilter filter, Map<Integer, String[]> attribute) {
		Query q = getQuery(filter, attribute);
		if (filter.getPage() < 1) {
			q.setFirstResult(0);
		} else {
			q.setFirstResult((filter.getPage() - 1) * pageSize);
		}
		q.setMaxResults(pageSize);

		return q.getResultList();
	}

	@Override
	public int getTotalRecordFilter(int pageSize, ProductFilter filter, Map<Integer, String[]> attribute) {
		return getQuery(filter, attribute).getResultList().size();
	}

	private Query getQuery(ProductFilter filter, Map<Integer, String[]> attribute) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		List<Predicate> predList = new ArrayList<>();

		if (Validate.checkStringNotEmptyOrNull(filter.getSearch())) {
			predList.add(builder.like(root.get("name"), "%" + filter.getSearch() + "%"));
		}
		
		if (filter.getStartPrice() >= 0) {
			predList.add(builder.or(builder.gt(root.get("price"), filter.getStartPrice()),
					builder.equal(root.get("price"), filter.getStartPrice())));
		}
		
		if (filter.getEndPrice() > 0 && filter.getStartPrice() < filter.getEndPrice()) {
			predList.add(builder.or(builder.lt(root.get("price"), filter.getEndPrice()),
					builder.equal(root.get("price"), filter.getEndPrice())));
		}
		
		if (filter.getBrandIds() != null && filter.getBrandIds().length > 0) {
			List<Integer> listBrandId = new ArrayList<Integer> ();
			for (int brandId : filter.getBrandIds()) {
			    listBrandId.add(brandId);
			}
			predList.add(root.get("brandId").in(listBrandId));
		}
		
		if (filter.getCategoryId() > 0) {
			Join<Product, Category> join = root.join("categories", JoinType.INNER);
			predList.add(builder.equal(join.get("id"), filter.getCategoryId()));
		}
		
		if (attribute != null && attribute.size() > 0) {
			Join<Product, ProductAttributeValue> join = root.join("productAttributeValues", JoinType.INNER);
			List<String> values = new ArrayList<>();
			attribute.forEach((k, v) -> {
				for (String value : v) {
					values.add(value);
				}
			});
			predList.add(join.get("value").in(values));
		}

		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		query.where(predArray);

		List<Order> orderList = new ArrayList<>();
		switch (Validate.checkStringNotEmptyOrNull(filter.getSort())? filter.getSort() : "") {
			case "new":
				orderList.add(builder.desc(root.get("createdDate")));
				break;
			case "high":
				orderList.add(builder.desc(root.get("price")));
				break;
			case "short":
				orderList.add(builder.asc(root.get("price")));
				break;
			case "az":
				orderList.add(builder.asc(root.get("name")));
				break;
			case "za":
				orderList.add(builder.desc(root.get("name")));
				break;
			case "sell":
				// do some thing
			default:
				orderList.add(builder.asc(root.get("createdDate")));
		}
		query.orderBy(orderList).distinct(true);
		return em.createQuery(query);
	}

	@Override
	public List<Product> getProductTheSame(String productId) {
		Product product = pr.getById(productId);
		if (product != null) {
			ProductFilter filter = new ProductFilter();
			filter.setQ(product.getName().substring(0, 2));
			filter.setB(new int[] {product.getBrand().getId()} );
			filter.setSp(product.getPrice().intValue() - 5000000 > 0? product.getPrice().intValue() - 5000000 : 0);
			filter.setEp(product.getPrice().intValue() + 5000000);
			filter.setP(1);
			return this.getFilter(12, filter, null);
		}
		return pr.findTop6ByOrderByCreatedDateDesc();
	}

	@Override
	public List<Product> getProductPricingInMonth(int year, int month) {
		Date start = new Date(year, month, 1);
		Date end;
		if (month == 12) {
			end = new Date(year + 1, 1, 1);
		} else {			
			end = new Date(year, month + 1, 1);
		}
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		List<Predicate> predList = new ArrayList<>();
		

		Join<Product, OrderDetail> typeJoin = root.join("orderDetails", JoinType.INNER);
		
		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		query.where(predArray);
		return em.createQuery(query).getResultList();
	}
}
