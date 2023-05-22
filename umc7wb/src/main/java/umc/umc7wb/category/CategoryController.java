package umc.umc7wb.category;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CategoryController() {}
    /**
     * 등록 API
     */

    @ResponseBody
    @PostMapping("/new1")
    public String newCategories(Category category) {
        System.out.println(category.getId());
       String name = category.getName();
       //String ss = "{\n\t\"categoryID\":10000,\n\t\"categoryName\":\"book\"\n}";
        String ss = "카테고리명:book 등록되었습니다";
        return ss;
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public String delete() {
        //String ss = "{\n\t\"categoryID\":10000,\n\t\"categoryName\":\"book\"\n}";
        String ss = "ID:2 삭제";
        return ss;
    }

    @ResponseBody
    @PatchMapping("/update/{id}")
    public String update(String sql, Object[] param) {
        String ss = "카테고리ID:200 수정되었습니다.";
        //String ss = "ID:2 업데이트 되었습니다.";
        return ss;
    }

    @ResponseBody
    @GetMapping("/find/{id}")
    public Category viewone() {
       // String ss = "{\n\t\"id\":200,\n\t\"name\":\"coding\"\n}";
        Category category2 = new Category();
        category2.setName("coding");
        category2.setId(200L);
        return category2;
    }

    @ResponseBody
    @GetMapping("/find")
    public String view1(@RequestParam(value = "num", required = false)int num) {
       return "num = " + num;
    }

    @ResponseBody
    @PostMapping("/new")
    public Category newCategory(@RequestBody Category category) {
        System.out.println("new");
        System.out.println(category.getId());
        Category category2 = new Category();
        category2.setName(category.getName());
        System.out.println(category.getName());
        category2.setId(category.getId());
        return category2;

    }


    @ResponseBody
    @PostMapping("/new2")
    public Category newCategory2(@RequestBody CreateCategoryRequest request) {
        Category category = new Category();
        Long n = 2000L;
        category.setId(n);
        category.setName("book");
        System.out.println(category.getName());
        return category;

    }

    @ResponseBody
    @GetMapping("/all")
    public List<Category> view(){
        return categoryService.findAll();
    }
    @Data
    static class CreateCategoryRequest{
        //        @NotEmpty
        private String name;
    }

    @Data
    static class CreateCategoryResponse{
        private Long id;

        public CreateCategoryResponse(Long id) {
            this.id = id;
        }
    }
}
