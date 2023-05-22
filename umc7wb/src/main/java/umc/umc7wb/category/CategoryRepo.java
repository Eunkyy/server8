package umc.umc7wb.category;

import umc.umc7wb.category.Category;

import java.util.*;

public class CategoryRepo {
    private static Map<Long, Category> ct = new HashMap<>();
    private static Long sequence = 100L;

    public Category save(Category category) {
   //     category.setId(++sequence);
        ct.put(category.getId(), category);
        return category;
    }

    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(ct.get(id));
    }

    public List<Category> findAll() {
        return new ArrayList<>(ct.values());
    }

    public void clearCategory() {
        ct.clear();
    }
}
