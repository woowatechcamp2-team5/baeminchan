package codesquad.product.service;

import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import codesquad.product.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable("categories")
    public List<Category> getMainCategories() {
        return categoryRepository.findAllByParentCategory(null);
    }

    @Cacheable("categories")
    public List<Category> getSubCategories(Long id) {
        Category mainCategory = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return categoryRepository.findAllByParentCategory(mainCategory);
    }

    public Category create(CategoryDto dto) {
        if (dto.isMainCategory()) {
            return categoryRepository.save(Category.builder().title(dto.getTitle()).build());
        }

        Category parentCategory = categoryRepository.findById(dto.getParentCategoryId()).orElse(null);
        return categoryRepository.save(Category.builder().title(dto.getTitle()).parentCategory(parentCategory).build());
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
