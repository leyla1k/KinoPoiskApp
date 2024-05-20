Чтобы запустить приложение можно скачать apk https://disk.yandex.ru/client/disk?source=main-loginmenu ТОКЕН НЕ ДЕЙСТВУЙЮЩИЙ!(

Screencast https://github.com/leyla1k/KinoPoiskAvitoApp/assets/91836312/8d099e04-ef9c-46d0-838d-131c9da24bc8

Выполнено:
1. Если история поиска сохраняется (последние 20 результатов)
2. Если поиск будет осуществляться не при каждом вводе символа, а в момент когда с ввода последнего символа прошла 1 секунда (debounce)
3. Если реализована пагинация: 
для отзывов пользователей
4. Если постеры отображаются в виде «карусели»
5. Если для пользователя есть возможность установить фильтры: по жанру, по стране производства, по типу контента (сериал/фильм), по году выхода, по возрастному рейтингу
6. (*реализовано локальное сохранение запросов) Если есть кэширование запросов и реализована возможность работы приложения в офлайн-моде с поиском из уже закэшированных фильмов
Начато внедрение зависимостей
итд

Примеры запросов. Ответы стандартные
   
    fun getFilmsList() https://api.kinopoisk.dev/v1.4/movie?page=1

    fun getFilmById() https://api.kinopoisk.dev/v1.4/movie/1143242

    fun getFilmsListByFilter() Например, https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10&year=2000&ageRating=18&countries.name=%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F&countries.name=%D0%A1%D0%A8%D0%90&genres.name=%D0%BA%D1%80%D0%B8%D0%BC%D0%B8%D0%BD%D0%B0%D0%BB&genres.name=%D0%BC%D0%B5%D0%BB%D0%BE%D0%B4%D1%80%D0%B0%D0%BC%D0%B0
    
    fun getFilmsByName() https://api.kinopoisk.dev/v1.4/movie/search?page=1&query=%D0%B0%D0%B2%D0%B8%D1%82%D0%BE

    fun getPosters() https://api.kinopoisk.dev/v1.4/image?page=1&selectFields=url&movieId=1143242

    fun getReviews() https://api.kinopoisk.dev/v1.4/review?page=1&selectFields=title&selectFields=type&selectFields=review&movieId=1143242
      


