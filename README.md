## Lesson_17_RestAPI

В ветке main разработано 5 простых автотестов на запросы из https://reqres.in/
Теорию для понимания разницы в запросах я брала тут https://mcs.mail.ru/blog/vvedenie-v-rest-api 

## Lesson_18_RestAPI

В ветке branch18 код автотеста на авторизацию на сайте магазина http://demowebshop.tricentis.com
Добавены приватные данные с библиотекой owner, подключен jenkins, запуск производится на удаленном веб-драйвере selenoid,
так же добавлены аттачменты для ui-тестов.

1. Для удаленного запуска на всех тестов используется команда:
```
clean test -Dbrowser=${BROWSER} -DbrowserSize=${BROWSER_SIZE} -Dremote=${REMOTE}
```


2. Для удаленного запуска теста с артифактами используется команда:
```
clean demowebshop -Dbrowser=${BROWSER} -DbrowserSize=${BROWSER_SIZE} -Dremote=${REMOTE}
```
