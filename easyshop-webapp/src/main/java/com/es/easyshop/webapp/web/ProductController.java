package com.es.easyshop.webapp.web;

import com.es.easyshop.webapp.clients.catalog.CatalogServiceClient;
import com.es.easyshop.webapp.clients.catalog.PagedResult;
import com.es.easyshop.webapp.clients.catalog.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final CatalogServiceClient catalogService;

    ProductController(CatalogServiceClient catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String productsPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {
        log.info("Fetching products for page: {}", pageNo);
        model.addAttribute("pageNo", pageNo);
        return "products";
    }

    @GetMapping("/api/products")
    @ResponseBody
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        log.info("Fetching products for page: {}", pageNo);
        return catalogService.getProducts(pageNo);
    }
}
