package csd214.app.services;

import csd214.app.entities.ProductEntity;
import csd214.app.entities.PublicationEntity;
import csd214.app.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Tells Spring IoC: "I am a Service. Build me and give me my dependencies."
public class BookstoreService {

    private final ProductRepository repository;

    // INJECTION: Spring Boot sees this constructor and automatically injects the repository!
    public BookstoreService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional // Ensures database operations safely commit or rollback
    public void performSale(Long id) {
        // findById returns an Optional in Spring, so we use .orElse(null)
        ProductEntity item = repository.findById(id).orElse(null);
        if (item == null) return;

        if (item instanceof PublicationEntity pub) {
            if (pub.getCopies() > 0) {
                pub.setCopies(pub.getCopies() - 1);
                repository.save(pub);
                System.out.println("Sold 1 copy of: " + pub.getTitle());
            } else {
                System.out.println("Out of stock!");
            }
        } else {
            System.out.println("Sold: " + item.getName());
        }
    }
}