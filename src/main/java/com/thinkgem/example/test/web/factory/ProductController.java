/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.example.test.web.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.example.test.entity.factory.Product;
import com.thinkgem.example.test.service.factory.ProductService;

/**
 * 产品Controller
 * @author ThinkGem
 * @version 2016-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/test/factory/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@ModelAttribute
	public Product get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return productService.get(id);
		}else{
			return new Product();
		}
	}
	
	@RequiresPermissions("test:factory:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			product.setCreateBy(user);
		}
        Page<Product> page = productService.find(new Page<Product>(request, response), product); 
        model.addAttribute("page", page);
		return "modules/" + "test/factory/productList";
	}

	@RequiresPermissions("test:factory:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		model.addAttribute("product", product);
		return "modules/" + "test/factory/productForm";
	}

	@RequiresPermissions("test:factory:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存产品'" + product.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/factory/product/?repage";
	}
	
	@RequiresPermissions("test:factory:product:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		productService.delete(id);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/test/factory/product/?repage";
	}

}
