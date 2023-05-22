package umc.umc7wb.category;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.umc7wb.category.Category;
import umc.umc7wb.category.CategoryController;
import umc.umc7wb.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CategoryService categoryService;

    @Autowired
    public TestController() {}

    /**
     * 로그 테스트 API
     * [GET] /test/log
     * @return String
     */
    @ResponseBody
    @GetMapping("/log")
    public String getAll() {
        System.out.println("테스트");
//        trace, debug 레벨은 Console X, 파일 로깅 X
//        logger.trace("TRACE Level 테스트");
//        logger.debug("DEBUG Level 테스트");

//        info 레벨은 Console 로깅 O, 파일 로깅 X
        logger.info("INFO Level 테스트");
//        warn 레벨은 Console 로깅 O, 파일 로깅 O
        logger.warn("Warn Level 테스트");
//        error 레벨은 Console 로깅 O, 파일 로깅 O (app.log 뿐만 아니라 error.log 에도 로깅 됨)
//        app.log 와 error.log 는 날짜가 바뀌면 자동으로 *.gz 으로 압축 백업됨
        logger.error("ERROR Level 테스트");

        return "Success Test";
    }

    @ResponseBody
    @PostMapping("/new2")
    public CategoryController.CreateCategoryResponse newCategory2(@RequestBody CategoryController.CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setName(request.getName());
        Long id = categoryService.join(category);
        return new CategoryController.CreateCategoryResponse(id);

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
