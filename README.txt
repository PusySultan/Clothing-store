
                        Clothing store.
___________________________________________________________________

    Приложение работает на порту 8080 (http://localhost:8080)
обработка запросов разделена по контроллерам, для начала работы
необходимо зарегистрироваться и войти в свой аккаунт.

___________________________________________________________________
                                                                  |
    * Для регистрации:                                            |
    Выполнить POST запрос http://localhost:8080/person/reg        |
и передать в теле запроса:                                        |
                                                                  |
    {                                                             |
        "lastName" : "Your last name",                            |
        "name" : "Your name",                                     |
        "patronymic" : "Your patronymic",                         |
        "email" : "any.com",                                      |
        "password" : "any password"                               |
    }                                                             |
                                                                  |
__________________________________________________________________|

___________________________________________________________________
                                                                  |
    Для входа в аккаунт:                                          |
    Выполнить GET запрос http://localhost:8080/person/aunt        |
и передать в теле запроса:                                        |
                                                                  |
    {                                                             |
        "email": "any.com",                                       |
        "password": "any password"                                |
    }                                                             |
                                                                  |
    Для полного теста:                                            |
    Выполнить GET запрос http://localhost:8080/person/aunt        |
    и передать в теле запроса:                                    |
                                                                  |
    {                                                             |
        "email": "Admin",                                         |
        "password": "qwerty"                                      |
    }                                                             |
                                                                  |
    В этом случае вы войдете как администратор и сможете          |
специализированным для него контроллером.                         |
                                                                  |
__________________________________________________________________|

Базовый функционал (Доступен всем пользователям после аутентификации):

    1. GET request:
        http://localhost:8080/person/aunt/out - выход из аккаунта.

    2. PUT request:
        http://localhost:8080/person/reg/{id} - изменение данных
        пользователя, в теле запроса передается новые данные
        как при регистрации *.

    3. DELETE request:
        http://localhost:8080/person/reg/{id} - удаление пользователя.

    4. GET request:
        http://localhost:8080/person/find - получить все
        товары в магазине.

    5. GET request:
        http://localhost:8080/person/find?type=ANYTYPE - Получить все
        товары отсортированные по типу ANY TYPE.

    6. GET request:
        http://localhost:8080/person/find?brand=ANYBRAND - Получить все
        товары отсортированные по типу ANY BRAND.

    7. GET request:
        http://localhost:8080/person/find?maxCost=x - Получить все
        товары чья цены меньше x.

    8. GET request:
        http://localhost:8080/person/find?minCost=x - Получить все
        товары чья цены выше x.

    9. GET request:
        http://localhost:8080/person/basket/add/{id} - Добавить товар
        в корзину с ID = id.

    10. GET request:
        http://localhost:8080/person/basket/remove/{id} -Удалить товар
        из корзины с ID = id.

    11. GET request:
        http://localhost:8080/person/basket/get - Получить свою корзину
        товаров

    12. GET request:
        http://localhost:8080/person/basket/buy/{id} - Выкупить товар
        из корзины с ID = id.

    13. GET request:
        http://localhost:8080/person/basket/buy/all - Выкупить все товары
        из корзины.

    ... todo any function in future

Продвинутый функционал (Доступен только в режиме администратора):

    1. GET request:
        http://localhost:8080/person/admin - Получение всех пользователей.

    2. DELETE request:
        http://localhost:8080/person/admin - Удаление всех пользователей.

    ... todo any function in future