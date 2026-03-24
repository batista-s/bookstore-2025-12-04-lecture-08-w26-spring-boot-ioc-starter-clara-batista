package csd214.app.services;

import csd214.app.entities.*;
import csd214.app.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

public class MusicCollectionService {

    private final ProductRepository repository;

    // INJECTION: Spring Boot sees this constructor and automatically injects the repository!
    public MusicCollectionService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void trackDownload(Long id) {
        ProductEntity item = repository.findById(id).orElse(null);
        if (item == null) return;

        if (!(item instanceof DigitalMusicEntity digital)) {
            System.out.println("Item is not digital music. No download tracked.");
            return;
        }

        if (digital.getLink() == null || digital.getLink().isBlank()) {
            System.out.println("Digital music has no valid link. Download not tracked.");
            return;
        }

        digital.setDownloadCount(digital.getDownloadCount() + 1);
        repository.save(digital);

        System.out.println("Tracked download for: " + digital.getTitle()
                + " | total downloads = " + digital.getDownloadCount());
    }

}
