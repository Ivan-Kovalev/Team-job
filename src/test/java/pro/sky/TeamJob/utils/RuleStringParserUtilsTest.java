package pro.sky.TeamJob.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RuleStringParserUtilsTest {

    @Test
    public void isRequestValid() {
        Assertions.assertTrue(RuleStringParserUtils.isRequestValid("USEROF:Валидный%NOTUSEROF:Запрос%NOTUSEROF:Выглядит%ACTIVEUSEROF:так"));
        Assertions.assertFalse(RuleStringParserUtils.isRequestValid("ЛюбойНеВалидныйЗапрос"));
    }

    @Test
    public void isRequestValidButArgumentsIsTooLow() {
        IllegalArgumentException illegalArgumentExceptionUserOfTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("USEROF:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила USEROF должно равняться 1", illegalArgumentExceptionUserOfTooLow.getMessage());

        IllegalArgumentException illegalArgumentExceptionNotUserOfTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("NOTUSEROF:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила NOTUSEROF должно равняться 1", illegalArgumentExceptionNotUserOfTooLow.getMessage());

        IllegalArgumentException illegalArgumentExceptionTopupTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUP:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила TOTUP должно равняться 1", illegalArgumentExceptionTopupTooLow.getMessage());

        IllegalArgumentException illegalArgumentExceptionTopupgtspendTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUPGTSPEND:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила TOPUPGTSPEND должно равняться 1", illegalArgumentExceptionTopupgtspendTooLow.getMessage());

        IllegalArgumentException illegalArgumentExceptionSpendsgtTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("SPENDSGT:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила SPENDSGT должно равняться 2", illegalArgumentExceptionSpendsgtTooLow.getMessage());

        IllegalArgumentException illegalArgumentExceptionActiveUserOfTooLow = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("ACTIVEUSEROF:"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила ACTIVEUSEROF должно равняться 1", illegalArgumentExceptionActiveUserOfTooLow.getMessage());
    }

    @Test
    public void isRequestValidButArgumentsIsTooMuch() {
        IllegalArgumentException illegalArgumentExceptionUserOfTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("USEROF:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила USEROF должно равняться 1", illegalArgumentExceptionUserOfTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionNotUserOfTooHigh = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("NOTUSEROF:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила NOTUSEROF должно равняться 1", illegalArgumentExceptionNotUserOfTooHigh.getMessage());

        IllegalArgumentException illegalArgumentExceptioTopupTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUP:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила TOTUP должно равняться 1", illegalArgumentExceptioTopupTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionTopupgtspendTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUPGTSPEND:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила TOPUPGTSPEND должно равняться 1", illegalArgumentExceptionTopupgtspendTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionSpendsgtTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("SPENDSGT:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила SPENDSGT должно равняться 2", illegalArgumentExceptionSpendsgtTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionActiveUserOfTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("ACTIVEUSEROF:Не:Валидный:Запрос"));
        Assertions.assertEquals("Ошибка! Число передаваемых аргумента для правила ACTIVEUSEROF должно равняться 1", illegalArgumentExceptionActiveUserOfTooMuch.getMessage());
    }

    @Test
    public void isRequestValidButArgumentsNotANumber() {
        IllegalArgumentException illegalArgumentExceptioTopupTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUP:Не:число"));
        Assertions.assertEquals("Ошибка! Второй переданный аргумент для правила TOTUP должен быть числом", illegalArgumentExceptioTopupTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionSpendsgtTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("SPENDSGT:НеЧисло:ИЭтоНеЧисло"));
        Assertions.assertEquals("Ошибка! Переданный аргумент для правила SPENDSGT должен быть числом", illegalArgumentExceptionSpendsgtTooMuch.getMessage());

        IllegalArgumentException illegalArgumentExceptionTopupsgtTooMuch = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("TOPUPSGT:НеЧисло"));
        Assertions.assertEquals("Ошибка! Переданный аргумент для правила TOPUPSGT должен быть числом", illegalArgumentExceptionTopupsgtTooMuch.getMessage());
    }

    @Test
    public void isRequestValidThrowsException() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> RuleStringParserUtils.isRequestValid("Валидный:Запрос%Выглядит:вот:так"));
        Assertions.assertEquals("No enum constant pro.sky.TeamJob.dto.Queries.Валидный", illegalArgumentException.getMessage());
    }
}
