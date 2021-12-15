package blin.ann;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class OzonTest {

    @DisplayName("Поиск бренда Paco Rabanne на сайте Озон")
    @Tag("blocker")
    @Test
    void ozonSearchPacoTest () {
        open("https://www.ozon.ru/");
        $(".f9j4").click();
        $(".f9j4 [type = 'text']").setValue("Paco Rabanne").pressEnter();
        $$(".widget-search-result-container.a9x2").shouldHave(texts("Paco Rabanne"));
    }

    //@ValueSource (strings = {"Paco Rabanne", "Prada"})
    @CsvSource({
            "Paco Rabanne,Paco Rabanne Lady Million Парфюмерная вода 30 мл",
            "Prada,Сумка на плечо Prada Milano"
    })
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск бренда {1} на сайте Озон")
    void ozonSearchPradaTest (String searchQuery) {
        open("https://www.ozon.ru/");
        $(".f9j4").click();
        $(".f9j4 [type = 'text']").setValue(searchQuery).pressEnter();
        $$(".widget-search-result-container.a9x2").shouldHave(texts(searchQuery));
    }
}
