# Поиск ближайших аэропортов

Это приложение позволяет искать 5 ближайших аэропортов к указанным координатам. Для поиска используется алгоритм k-d дерева.

# Зависимости  Maven
Файл pom.xml должен содержать репозиторий и зависимости для использования библиотеки готовой реализации kdtree, а также аннотаций lombok.
Также необходимо в settings.xml внутри блока <settings> добавить следующее: 
        <profiles>
            <profile>
                <id>airports-searcher</id>
                <repositories>
                    <repository>
                        <id>harium-mvn-repo</id>
                        <url>https://raw.github.com/harium/kdtree/mvn-repo/</url>
                    </repository>
                </repositories>
            </profile>
        </profiles>
        <activeProfiles>
            <activeProfile>airports-searcher</activeProfile>
        </activeProfiles>
# Формат данных
Данные об аэропортах хранятся в файле src/main/resources/airports.dat,
который содержит информацию в формате CSV. Приложение использует следующие поля:

    Имя аэропорта
    Широта
    Долгота
    
# Авторы
Автор кода: github.com/Sharapkov
Автор библиотеки KDTtree: hgithub.com/Harium
