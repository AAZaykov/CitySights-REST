# REST-API-CitySights
REST-сервис для хранения данных о городских достопримечательностях

Структура данных:

Город:
| Поля                            | 
| --------------------------------|
| Идентификатор (порядковый номер)| 
| Название города                 | 
| Численность населения           | 
| Наличие метро                   | 
| Страна                          | 

Достопримечательность:
| Поля                                                                                   | 
| ---------------------------------------------------------------------------------------|
| Идентификатор                                                                          | 
| Название достопримечательности                                                         | 
| Дата постройки                                                                         | 
| Краткое описание                                                                       | 
| Тип достопримечательности (enum, например: Здание/Сооружение/Музей/Памятник/Заповедник)| 
| Идентификатор города                                                                   | 

REST-сервис предоставляет следующие методы:

- Получить все достопримечательности (опционально можно передать параметр для сортировки по наименованию достопримечательности, параметр для фильтрации по типу достопримечательности)
- Получить все достопримечательности конкретного города
- Добавить город
- Добавить достопримечательность
- Изменение данных по городу (возможно изменение только полей: Численность населения, Наличие метро)
- Изменение данных по достопримечательности (возможно изменение только полей: Краткое описание)
- Удаление достопримечательности из справочника

Сервис при первом запуске самостоятельно создаёт необходимые объекты в БД с помощью Liquibase.
Используется PostgreSQL.

Для реализации используется Java 8, Spring Boot, Hibernate, PostgreSQL, Liquibase.

## Инструкция по запуску
- Склонировать репозиторий (git clone)
- В /resources/application.properties указать данные, необходимые для подключения к базе данных
- Запустить приложение (Можно с помощью mvn spring-boot:run)

## REST API

Получение всех достопримечательностей:
**GET /sights**

При отправке запроса можно (опционально) указать параметры для фильтрации по типу достпримечательности и сортировки по имени: 
filterByType(BUILDING, CONSTRUCTION, MONUMENT, MUSEUM), sortByName(true/false)

    Пример запроса: http://localhost:8080/sights?filterByType=BUILDING&sortByName=true

Получить все достопримечательности конкретного города:
**GET /sights/{cityId}**

    Пример запроса: http://localhost:8080/sights/1

Добавить город:
**POST /cities/add**

При отправке запроса необходимо указать все параметры города: name, population, metro, country

    Пример запроса: http://localhost:8080/cities/add?name=Пермь&population=1042763&metro=false&country=Россия
    
Добавить достопримечательность:
**POST /sights/add**

При отправке запроса необходимо указать все параметры достпримечательности: name, date, description, typeSight, cityId

Возможные значения для typeSight: BUILDING, CONSTRUCTION, MONUMENT, MUSEUM

    Пример запроса: http://localhost:8080/sights/add?name=Драмтеатр&date=31-12-1800&description=Драматический театр&typeSight=BUILDING&cityId=1

Изменение данных по городу:
**POST cities/edit/{cityId}**

При отправке запроса можно (опционально) указать параметры для редактирования города: population, metro

    Пример запроса: http://localhost:8080/cities/edit/4?population=1100000&metro=true

Изменение данных по достопримечательности:
**POST /sights/edit/{sightId}**

При отправке запроса для редактирования можно указать только один параметр(в соответствии с условием задания): shortDescription

    Пример запроса: http://localhost:8080/sights/edit/9?shortDescription=Рекомендуется для посещения

Удаление достопримечательности из справочника:
**DELETE /sights/{sightId}**

    Пример запроса: http://localhost:8080/sights/1
