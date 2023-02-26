# Поиск ближайших аэропортов

Это приложение позволяет искать 5 ближайших аэропортов к указанным координатам. Для поиска используется алгоритм k-d дерева.

# Сборка и запуск
Сборка проекта

Чтобы собрать проект, необходимо запустить команду:

mvn clean package

Эта команда выполнит очистку проекта и упакует его в исполняемый JAR файл.

Запуск проекта

Чтобы запустить проект, необходимо выполнить команду:

java -jar target/airports-search-1.0-SNAPSHOT.jar

# Зависимости  Maven
Файл pom.xml должен содержать репозиторий и зависимости для использования библиотеки готовой реализации kdtree, а также аннотаций lombok.
  
# Формат данных
Данные об аэропортах хранятся в файле src/main/resources/airports.dat,
который содержит информацию в формате CSV. Приложение использует следующие поля:

    Имя аэропорта
    Широта
    Долгота
    
# Авторы
Автор кода: github.com/Sharapkov

Автор библиотеки KDTtree: github.com/Harium
