package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.TeamJob.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
