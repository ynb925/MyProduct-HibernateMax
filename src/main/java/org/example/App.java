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
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass(Product.class);
        con.addAnnotatedClass(ProductVersion.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties());
        SessionFactory sf = con.buildSessionFactory(sBuilder.build());

        ProductRepository productRepository = new ProductRepository(sf);
        ProductVersionRepository productVersionRepository = new ProductVersionRepository(sf);

        Product product = new Product();
        product.setName("alalalal");
        product.setPrice(2123123455);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setDetails("rsgsrgfd");
        productVersion.setCreator("eerrfeef");
        productVersion.setVersion(66789);

        ProductVersion productVersion1 = new ProductVersion();
        productVersion1.setDetails("aded");
        productVersion1.setCreator("eeriliuujyrfeef");
        productVersion1.setVersion(976);

        productVersion.setOwner(product);
        productVersion1.setOwner(product);
        product.setProductVersion(List.of(productVersion1));

        System.out.println("----------  SAVE  Prod  ------------");
        productRepository.save(product);
        System.out.println(product);
        System.out.println(productVersion);

        System.out.println("-----------  FIND BY ID  Prod  -----------");
        Product byId = productRepository.findById(17);
        System.out.println(byId);


        System.out.println("-----------  DEL BY ID  Prod  -----------");
        boolean delId = productRepository.deleteById(9);
        System.out.println(delId);

        System.out.println("-----------  FIND ALL Prod  -----------");
        List<Product> findAll = productRepository.findAll();
        System.out.println(findAll);

        System.out.println("----------  SAVE  Prod ver  ------------");
        productVersionRepository.savePVR(productVersion);
        System.out.println(product);
        System.out.println(productVersion);

        System.out.println("-----------  FIND BY ID  Prod ver -----------");
        ProductVersion byIdPVR = productVersionRepository.findByIdPVR(2);
        System.out.println(byIdPVR);

        System.out.println("-----------  DEL BY ID  Prod  ver  -----------");
        boolean delIdPVR = productVersionRepository.deleteByIdPVR(1);
        System.out.println(delIdPVR);

        System.out.println("-----------  FIND ALL Prod ver -----------");
        List<ProductVersion> findAllPVR = productVersionRepository.findAllPVR();
        System.out.println(findAllPVR);

        productRepository.close();
        productVersionRepository.close();
    }
}

