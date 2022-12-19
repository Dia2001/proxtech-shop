package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.entities.Category;

public interface CategoryService {
   boolean create(Category crg);
   boolean updateCtg(int id,String data);
   List<Category> loadAll();
   List<Category> searchCtg(String search);
}
