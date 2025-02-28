package pro.sky.TeamJob.dto;

/**
 * Класс хранящий константные названия отдельных правил для их комбинирования
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public enum Queries {

    /** Пользователь использует продукт */
    USEROF,

    /** Пользователь не использует продукт */
    NOTUSEROF,

    /** Запрос пополнения. Пользователь пополнял продукт на указанную сумму */
    TOPUP,

    /** Сумма пополнения больше суммы трат */
    TOPUPGTSPEND,

    /** Сумма пополнения меньше суммы трат */
    TOPUPGTSPENDLESS,

    /** Сумма списаний по продукту больше "n" */
    SPENDSGT,

    /** Сумма пополнений по продукту больше "n" */
    TOPUPSGT,

    /** Пользователь активно использует продукт определенного типа */
    ACTIVEUSEROF,

    /** Пользователь активно использует продукт определенного типа минимум N раз больше чем X за операцию*/
    ACTIVEUSERANDPAYABLE;
}