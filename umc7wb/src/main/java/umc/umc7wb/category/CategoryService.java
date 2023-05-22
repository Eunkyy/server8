package umc.umc7wb.category;

import umc.umc7wb.category.Category;
import umc.umc7wb.category.CategoryRepo;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final CategoryRepo categoryRepo;//memberRepository를 new에서 생성하지 않고 외부에서 넣어줌.

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * 생성
     */
    public Long join(Category category){
       // validateDuplicateMember(category);//중복회원 검증.
        categoryRepo.save(category);
        return category.getId();
    }
//
//    private void validateDuplicateMember(Category category) {
//        categoryRepo.findById(category.get())
//                .ifPresent(m -> {
//                    throw  new IllformedLocaleException("이미 존재하는 회원입니다.");
//                });
//    }

    /**
     * 전체 카테고리 조회
     */
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
    public Optional<Category> findOne(Integer id) {
        return categoryRepo.findById(id);
    }
}
