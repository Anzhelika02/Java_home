package ru.students.airline.persistence.repository;//Указываем на принадлежность данного файла к определенному пакету `ru.students.airline.persistence.repository` в Java.

import org.springframework.data.jpa.repository.JpaRepository;//Директивой import, которая позволяет использовать `JpaRepository` из пакета `org.springframework.data.jpa.repository` в данном файле. 
import org.springframework.data.jpa.repository.Query;//Импорт аннотации `@Query` из пакета `org.springframework.data.jpa.repository`. Эта аннотация используется для определения пользовательских SQL-запросов в репозитории.

import org.springframework.stereotype.Repository;//Директива import используется для импорта аннотации `@Repository` из пакета `org.springframework.stereotype`. `@Repository` указывает на то, что данный интерфейс является репозиторием Spring, что автоматически создаст бин Spring на основе этого интерфейса.
import ru.students.airline.persistence.entity.Flight;//Добавляем класс `Flight` из пакета `ru.students.airline.persistence.entity` в текущий файл, делая его доступным для использования в данном файле. `Flight` представляет объект рейса в контексте данного приложения.


import java.time.LocalDate;//Этот оператор импортирует класс `LocalDate` из пакета `java.time`. `LocalDate` используется для представления даты без времени, таким образом, предоставляя более удобное представление даты.
import java.util.List;//Импортируем класс `List` из пакета `java.util`. `List` представляет собой интерфейс, который предоставляет различные методы для работы с коллекциями.
import java.util.Optional;//Импортируем класс `Optional` из пакета `java.util`. Он используется для более безопасной работы с потенциально отсутствующими значениями, обеспечивая явное обозначение возможности отсутствия значения и предотвращая ошибки NullPointerException.


@Repository//Эта строка является аннотацией `@Repository`, которая указывает, что интерфейс `FlightsRepo` является компонентом Spring и будет управляться контейнером Spring как репозиторий.
public interface FlightsRepo extends JpaRepository<Flight, Integer> {  //Ообъявляем интерфейс с именем `FlightsRepo`, который наследует методы от интерфейса `JpaRepository` для работы с сущностью типа `Flight` с ID типа `Integer`. Это означает, что `FlightsRepo` предоставляет методы для работы с сущностями `Flight`, такие как сохранение, поиск и удаление.
    @Query(value = "SELECT DISTINCT f.* FROM flights f JOIN flight_dates fd ON f.number = fd.flight_number " +
            "WHERE fd.date = ?1 AND f.departure_city = ?2 AND f.arrival_city = ?3 AND fd.reserved_seats + ?4 <= f.seats " +
            "ORDER BY f.cost",
    nativeQuery = true) //SQL-запрос ищет определенные рейсы на указанную дату, из указанного города отправления в указанный город прибытия с указанным числом доступных мест.

    List<Flight> findSomeFlights(LocalDate date, String dCity, String aCity, Integer seats); //Этот метод объявляет поиск рейсов с определенными критериями (датой, городом отправления, городом прибытия и числом доступных мест). Метод возвращает список рейсов, удовлетворяющих указанным критериям.

    Optional<Flight> findFlightByNumber(String number);//Этот метод объявляет поиск рейса по его номеру. Метод возвращает `Optional`, что указывает на то, что результат поиска может отсутствовать.
}
