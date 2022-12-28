package com.proxtechshop.admincontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.common.Validate;
import com.proxtechshop.entities.Image;
import com.proxtechshop.entities.Product;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.services.TypesProductService;
import com.proxtechshop.utils.FileUploadUtil;

@Controller
public class ManagementController {

	@Autowired
	ProductService productService;

	@Autowired
	TypesProductService typesProductService;

	@RequestMapping(Constants.ADMIN_PATH)
	public RedirectView adminMng(Model model) {
		return new RedirectView(Constants.ADMIN_DASHBOARD_PATH);
	}

	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH)
	public String ProductMng(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

//	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH)
//	public String MemberMng(Model model)
//	{
//		model.addAttribute("products",productService.getAllProduct());
//		return Constants.ADMIN_MEMBERSMNG_VIEW;
//	}
//	@RequestMapping(Constants.ADMIN_BRAND_PATH)
//	public String BrandMng(Model model) {
//		return Constants.ADMIN_BRAND_VIEW;
//	}
//	@RequestMapping(Constants.ADMIN_CATEGORIESMNG_PATH)
//	public String CategoryMng(Model model)
//	{
//		return Constants.ADMIN_CATEGORIESMNG_VIEW;
//	}
	/*
	 * @RequestMapping(Constants.ADMIN_ORDERMNG_PATH) public String OrderMng(Model
	 * model) { return Constants.ADMIN_ORDERMNG_VIEW; }
	 */
	@RequestMapping(Constants.ADMIN_PROFILE_PATH)
	public String ProfileAdminMng(Model model) {
		return Constants.ADMIN_PROFILE_VIEW;
	}

	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH + "/delete/{id}")
	public String DeleteProduct(@PathVariable("id") Optional<String> id, Model model) {
		if (id.isPresent()) {
			try {
				productService.DeleteProduct(id.get());
			} catch (Exception e) {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Sản phẩm không tồn tại.");
				// model.addAttribute("products",productService.getAllProduct());
				return findPaginated(1, "name", "asc", model);
			}
			model.addAttribute("flag", true);
			model.addAttribute("msg", "Xóa thành công");
			// model.addAttribute("products",productService.getAllProduct());
			return findPaginated(1, "name", "asc", model);
		}
		model.addAttribute("flag", false);
		model.addAttribute("msg", "Xóa không thành công");
		// model.addAttribute("products",productService.getAllProduct());
		return findPaginated(1, "name", "asc", model);
	}

	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH + "/filter")
	public String searchProduct(@RequestParam("search") String search, @RequestParam("category") int ctg,
			@RequestParam("brand") int brand, Model model) {
		// if no any param is empty, then search
		if (search != "" || brand != 0 || ctg != 0) {
			model.addAttribute("search", search);
			model.addAttribute("brand", brand);
			model.addAttribute("ctg", ctg);
			model.addAttribute("anchor", true);
		} else {
			model.addAttribute("anchor", null);
			model.addAttribute("msg", "Không để trống phần tìm kiếm");
		}
		return findPaginated(1, "name", "asc", model);
	}

	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH + "/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		
		int pageSize = 4;
		
		// handler search engine
		Page<Product> page;
		boolean flag = model.getAttribute("anchor") == null;
		if (flag) {
			page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		} else {
			String search = model.getAttribute("search").toString();
			int brand = (int) model.getAttribute("brand");
			int ctg = (int) model.getAttribute("ctg");

			page = productService.FilterAndPaginated(search, ctg, brand, pageNo, pageSize, sortField, sortDir);

			if (page.getContent().size() == 0) {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Không tìm thấy " + search);
			} else {
				model.addAttribute("flag", true);
				model.addAttribute("msg", "Tìm thấy " + page.getTotalElements() + " kết quả của " + search);
			}
		}
		List<Product> listProducts = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("brand", typesProductService.getAllBrand());
		model.addAttribute("category", typesProductService.getAllCategory());

		model.addAttribute("products", listProducts);
		return Constants.ADMIN_PRODUCTMNG_VIEW;
	}

	@GetMapping(Constants.ADMIN_FOFMPRODUCT_PATH)
	public String formProduct(@RequestParam(name = "id", required = false) String id, Model model) {
		if (Validate.checkStringNotEmptyOrNull(id)) {
			try {
				model.addAttribute("product", productService.getProductById(id));
				model.addAttribute("specifications", productService.showAtrsAndValues(id));
			} catch (Exception e) {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Không tìm thấy sản phẩm chỉnh sửa!");
				return findPaginated(1, "name", "asc", model);
			}
		} else {
			model.addAttribute("specifications", productService.showAtrsAndValues(null));
			model.addAttribute("product", new Product());
		}
		// add product attribute value , how can i add this effectively?
		return getFormProduct(model);
	}

	public String getFormProduct(Model model) {
		model.addAttribute("brand", typesProductService.getAllBrand());
		model.addAttribute("category", typesProductService.getAllCategory());
		return Constants.ADMIN_FOFMPRODUCT_VIEW;
	}

	@RequestMapping(value = Constants.ADMIN_FOFMPRODUCT_PATH, method = RequestMethod.POST)
	public String UpdateProduct(Product product, Model model,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		if (Validate.checkStringNotEmptyOrNull(product.getId())) {
			if (files != null) {
				productService.uploadListImage(product.getId(), files);
			}
			boolean flag = productService.updateProduct(product);
			if (flag) {
				model.addAttribute("flag", true);
				model.addAttribute("msg", "Sửa sản phẩm thành công!");
				return findPaginated(1, "name", "asc", model);
			} else {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Sửa không thành công!");
				return getFormProduct(model);
			}
		} else {
			if (files != null && files.length > 0) {				
				product = productService.createProduct(product, files[0]);
				files[0] = null;
			} else {				
				product = productService.createProduct(product, null);
			}
			if (product != null) {
				if (files != null && files.length > 1) {
					productService.uploadListImage(product.getId(), files);
				}
				model.addAttribute("flag", true);
				model.addAttribute("msg", "Thêm sản phẩm thành công!");
				return findPaginated(1, "name", "asc", model);
			} else {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Thêm không thành công!");
				return getFormProduct(model);
			}
		}
	}

	@GetMapping(Constants.ADMIN_FOFMPRODUCT_DELETE_THUMBNAIL_PATH)
	public String deleteThumbnail(@RequestParam String id, Model model) {
		Product product = productService.getProductById(id);
		String msg = "Xóa ảnh thất bại!";
		boolean flag = false;
		if (product != null) {
			Image image = null;
			for (Image imageTmp : product.getImages()) {
				image = imageTmp;
				break;
			}
			if (image != null) {
				product.getImages().remove(image);
				product.setImages(product.getImages());
				FileUploadUtil.removeFile(product.getThumbnail());
				product.setThumbnail(image.getThumbnail());
				flag = productService.updateProduct(product);
				msg = "Xóa ảnh thành công!";
			} else {
				msg = "Không thể xóa toàn bộ ảnh!";
			}
		}
		model.addAttribute("flag", flag);
		model.addAttribute("msg", msg);
		return formProduct(id, model);
	}

	@GetMapping(Constants.ADMIN_FOFMPRODUCT_DELETE_IMAGE_PATH)
	public String deleteImage(@RequestParam int id, @RequestParam String productId, Model model) {
		boolean flag = productService.deleteImage(id);
		if (flag) {
			model.addAttribute("flag", true);
			model.addAttribute("msg", "Xóa ảnh thành công!");
		} else {
			model.addAttribute("flag", false);
			model.addAttribute("msg", "Xóa ảnh thất bại!");
		}
		return formProduct(productId, model);
	}

	@RequestMapping(Constants.ADMIN_PATH + "/{page}")
	public RedirectView testAdmin(@PathVariable(name = "page", required = false) Optional<String> id, Model model) {
		switch (id.get()) {
		case "1":
			return new RedirectView(Constants.ADMIN_CUSTOMERSMNG_PATH);
		case "2":
			return new RedirectView(Constants.ADMIN_MEMBERSMNG_PATH);
		case "3":
			return new RedirectView(Constants.ADMIN_BRAND_PATH);
		case "4":
			return new RedirectView(Constants.ADMIN_CATEGORIESMNG_PATH);
		case "5":
			return new RedirectView(Constants.ADMIN_PRODUCTMNG_PATH);
		case "6":
			return new RedirectView(Constants.ADMIN_ORDERMNG_PATH);
		case "7":
			return new RedirectView(Constants.ADMIN_PROFILE_PATH);
		}
		return new RedirectView(Constants.ERROR_404_VIEW);
	}
}
