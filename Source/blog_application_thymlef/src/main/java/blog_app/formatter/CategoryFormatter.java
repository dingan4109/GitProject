package blog_app.formatter;

import blog_app.entity.Category;
import blog_app.service.CategoryService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {

    private   CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public Category parse(String id, Locale locale) throws ParseException {
        Category category = categoryService.findById(Integer.parseInt(id));
        return category;
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}
