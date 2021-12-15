package blin.ann;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class OzonTest {

    @DisplayName("Поиск бренда Paco Rabanne на сайте Озон")
    @Tag("blocker")
    @Test
    void ozonSearchNikeTest() {
        open("https://www.ozon.ru/");
        $(".f9j4").click();
        $(".f9j4 [type = 'text']").setValue("Nike").pressEnter();
        $$(".widget-search-result-container.a9x2").shouldHave(texts("Nike"));
    }

    //@ValueSource (strings = {"Nikee", "Prada"})
    @CsvSource({
            "Paco Rabanne,Термонаклейка для одежды, термопринт, принт на одежду, термоаппликация на одежду \"Nike\"",
            "Prada,Футболка North Sails Prada"
    })
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск бренда {1} на сайте Озон")
    void ozonSearchPradaTest(String searchQuery, String expectedResult) {
        open("https://www.ozon.ru/");
        $(".f9j4").click();
        $(".f9j4 [type = 'text']").setValue(searchQuery).pressEnter();
        $$(".widget-search-result-container.a9x2").shouldHave(texts(expectedResult));
    }

    @EnumSource(SearchQuery.class)
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск бренда {0} на сайте Озон")
    void ozonSearchSomethingTest(SearchQuery searchQuery ) {
        open("https://www.ozon.ru/");
        $(".f9j4").click();
        $(".f9j4 [type = 'text']").setValue(searchQuery.name()).pressEnter();
        $$(".container.a3o9").shouldHave(texts(searchQuery.name()));
    }
}
