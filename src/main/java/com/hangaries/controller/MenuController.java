package com.hangaries.controller;


import com.hangaries.model.Menu;
import com.hangaries.model.MenuIngrident;
import com.hangaries.service.menuIngridentService.Impl.MenuIngredientServiceImpl;
import com.hangaries.service.menuService.Impl.MenuServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private MenuIngredientServiceImpl menuIngredientService;

    /**
     * Get all menuItems
     *
     * @return
     * @throws Exception
     */
    @GetMapping("getAllMenuItems")
    @ResponseBody
    public ResponseEntity<List<Menu>> getAllMenuItems() throws Exception {
        logger.info("Get all menu items::");
        List<Menu> menuList = new ArrayList<Menu>();
        try {
            menuList = menuService.getAllMenuItems();
            return new ResponseEntity<List<Menu>>(menuList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting menuitems::" + ex.getMessage());
            return new ResponseEntity<List<Menu>>(menuList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all menuItems
     *
     * @return
     * @throws Exception
     */
    @GetMapping("getMenuItemsByRestroAndStore")
    @ResponseBody
    public ResponseEntity<List<Menu>> getMenuItemsByRestroAndStore(@RequestParam("restaurantId") String restaurantId, @RequestParam("storeId") String storeId) throws Exception {
        logger.info("Get all menu items for restaurantId = {} and storeId = {}.", restaurantId, storeId);
        List<Menu> menuList = new ArrayList<Menu>();
        try {
            menuList = menuService.getMenuItemsByRestroAndStore(restaurantId, storeId);
            return new ResponseEntity<List<Menu>>(menuList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting menuitems::" + ex.getMessage());
            return new ResponseEntity<List<Menu>>(menuList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all ingredients by productId
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("getMenuIngredientsByMenuId")
    @ResponseBody
    public ResponseEntity<List<MenuIngrident>> getIngredientsByMenuId(@RequestParam("productId") String productId, @RequestParam("restaurantId") String restaurantId,
                                                                      @RequestParam("storeId") String storeId) throws Exception {
        List<MenuIngrident> menuIngridentList = new ArrayList<MenuIngrident>();
        try {
            logger.info("Get ingredients::");
            menuIngridentList = menuIngredientService.getIngredientsByMenuId(productId, restaurantId, storeId);
            return new ResponseEntity<List<MenuIngrident>>(menuIngridentList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting Ingredients::" + ex.getMessage());
            return new ResponseEntity<List<MenuIngrident>>(menuIngridentList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getMenuIngredientsByRestoAndStoreId")
    @ResponseBody
    public ResponseEntity<List<MenuIngrident>> getMenuIngredientsByRestoAndStoreId(@RequestParam("restaurantId") String restaurantId,
                                                                                   @RequestParam("storeId") String storeId) throws Exception {
        List<MenuIngrident> menuIngridentList = new ArrayList<MenuIngrident>();
        try {
            logger.info("Get ingredients for restaurantId = {}, storeId = {}. ", restaurantId, storeId);
            menuIngridentList = menuIngredientService.getAllIngredientsByRestoAndStoreId(restaurantId, storeId);
            return new ResponseEntity<List<MenuIngrident>>(menuIngridentList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting Ingredients::" + ex.getMessage());
            return new ResponseEntity<List<MenuIngrident>>(menuIngridentList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllSections")
    @ResponseBody
    public ResponseEntity<List<String>> getSections(@RequestParam("restaurantId") String restaurantId, @RequestParam("storeId") String storeId) throws Exception {
        List<String> sections;
        try {
            logger.info("Get all sections for restaurantId = {}, storeId = {}. ", restaurantId, storeId);
            sections = menuService.getAllSections(restaurantId, storeId);
            return new ResponseEntity<List<String>>(sections, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting Sections::" + ex.getMessage());
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getDishesBySection")
    @ResponseBody
    public ResponseEntity<List<String>> getDishesBySection(@RequestParam("section") String section, @RequestParam("restaurantId") String restaurantId, @RequestParam("storeId") String storeId) throws Exception {
        List<String> sections;
        try {
            logger.info("Get Dishes for section = {}, restaurantId = {}, storeId = {}.", section, restaurantId, storeId);
            sections = menuService.getDishesBySection(section, restaurantId, storeId);
            return new ResponseEntity<List<String>>(sections, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting Sections::" + ex.getMessage());
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
