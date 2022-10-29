package com.tinybeans.backend.evaluation.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.repository.ItemRepository;

@Service
public class DBService {

    @Autowired
    private ItemRepository itemRepository;

    public void instantiateTestDatabase() throws ParseException {
        Item i1 = Item.builder().name("T-Shirt").description("Item 1 description")
                .photoUrl("https://cdn.shopify.com/s/files/1/0526/4123/5093/products/7_450x.jpg").price(10.0).build();
        Item i2 = Item.builder().name("Pants").description("Item 2 description").photoUrl(
                "https://media.wired.com/photos/611c5312798f0e2c853b702f/1:1/w_993,h_993,c_limit/Gear-Cargo-Pants-are-Back-1302952122.jpg")
                .price(20.0).build();
        Item i3 = Item.builder().name("Shoes").description("Item 3 description")
                .photoUrl("https://images.meesho.com/images/products/52483851/vje6h_512.jpg").price(30.0).build();
        Item i4 = Item.builder().name("Hat").description("Item 4 description").photoUrl(
                "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/1ada39a6-631f-4b4b-8ddd-3a60b775fed9/legacy91-golf-hat-vLJp5Q.png")
                .price(40.0).build();
        Item i5 = Item.builder().name("Socks").description("Item 5 description")
                .photoUrl("https://cdn.shopify.com/s/files/1/1132/3434/products/Split_Edit_2000x.jpg?v=1636753018")
                .price(50.0).build();
        Item i6 = Item.builder().name("Jacket").description("Item 6 description").photoUrl(
                "https://media.gq.com/photos/623cc30d9233ab2e539ed369/master/w_2000,h_1333,c_limit/jackets-14.jpg")
                .price(60.0).build();
        Item i7 = Item.builder().name("Gloves").description("Item 7 description")
                .photoUrl("https://m.media-amazon.com/images/I/618s1p165AL._AC_.jpg").price(70.0).build();
        Item i8 = Item.builder().name("Scarf").description("Item 8 description").photoUrl(
                "https://me.louisvuitton.com/images/is/image/lv/1/PP_VP_L/%D9%84%D9%88%D9%8A%D8%B3-%D9%81%D9%88%D9%8A%D8%AA%D9%88%D9%86-jhelam-scarf-s00-%D8%A7%D9%84%D8%A3%D9%88%D8%B4%D8%AD%D8%A9--M75362_PM2_Front%20view.jpg")
                .price(80.0).build();
        Item i9 = Item.builder().name("Sunglasses").description("Item 9 description").photoUrl(
                "https://cdn.shopify.com/s/files/1/1356/7869/products/affordable-sunglasses-black-on-black-smoke-fastlanes-front_large.png?v=1596654656")
                .price(90.0).build();

        this.itemRepository.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9));
    }
}
