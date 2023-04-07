package org.example;

import org.example.model.Product;
import org.example.model.ProductRepository;
import org.example.model.ProductVersion;
import org.example.model.ProductVersionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(ProductVersion.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sf = configuration.buildSessionFactory(sBuilder.build());

        ProductRepository productRepository = new ProductRepository(sf);
        ProductVersionRepository productVersionRepository = new ProductVersionRepository(sf);

        Product product = new Product();
        product.setName("someName");
        product.setPrice(22244);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setDetails("set any details text");
        productVersion.setCreator("set any creators text");
        productVersion.setVersion(66789);

        ProductVersion productVersion1 = new ProductVersion();
        productVersion1.setDetails("set any details text1");
        productVersion1.setCreator("set any creators text1");
        productVersion1.setVersion(976);

        productVersion.setProduct(product);
        productVersion1.setProduct(product);
        product.setProductVersion(List.of(productVersion1));

        System.out.println("----------  SAVE  Product  ------------");
        productRepository.save(product);
        System.out.println(product);
        System.out.println(productVersion);

        System.out.println("-----------  FIND BY ID  Product  -----------");
        Product productByID = productRepository.findById(17);
        System.out.println(productByID);


        System.out.println("-----------  DEL BY ID  Product  -----------");
        boolean productDeleteById = productRepository.deleteById(9);
        System.out.println(productDeleteById);

        System.out.println("-----------  FIND ALL Product  -----------");
        List<Product> findAllProduct = productRepository.findAll();
        System.out.println(findAllProduct);

        System.out.println("----------  SAVE  ProductVersion  ------------");
        productVersionRepository.save(productVersion);
        System.out.println(product);
        System.out.println(productVersion);

        System.out.println("-----------  FIND BY ID  ProductVersion -----------");
        ProductVersion ProductVersionByID = productVersionRepository.findById(2);
        System.out.println(ProductVersionByID);

        System.out.println("-----------  DEL BY ID  ProductVersion  -----------");
        boolean productVersionDeleteById = productVersionRepository.deleteById(1);
        System.out.println(productVersionDeleteById);

        System.out.println("-----------  FIND ALL ProductVersion -----------");
        List<ProductVersion> findAllProductVersion = productVersionRepository.findAll();
        System.out.println(findAllProductVersion);

        productRepository.close();
        productVersionRepository.close();
    }
}

