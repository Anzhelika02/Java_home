package ru.students.airline.persistence.repository; //Указываем на принадлежность данного файла к определенному пакету в Java. 

import org.springframework.data.jpa.repository.Query; //Директива import, которая позволяет использовать аннотацию `@Query` из пакета `org.springframework.data.jpa.repository`. Используется для определения пользовательских SQL-запросов в репозитории.
import org.springframework.data.repository.CrudRepository; //Импортируем интерфейс `CrudRepository` из пакета `org.springframework.data.repository`. `CrudRepository` предоставляет базовые методы для работы с сущностями, включая сохранение, удаление и поиск.

import ru.students.airline.persistence.entity.Ticket;//Этот оператор импортирует класс `Ticket` из пакета `ru.students.airline.persistence.entity` в текущий файл. Ticket` представляет конкретный билет.


import java.util.Optional; //Импортируем класс `Optional` из пакета `java.util`. `Optional` представляет контейнер, который может содержать или отсутствовать значение  и используется для представления результатов операций, которые могут возвращать какое-то значение или ничего.

public interface TicketsRepo extends CrudRepository<Ticket, Integer> { //Объявляем интерфейс с именем `TicketsRepo`, который расширяет (extends) интерфейс `CrudRepository` для работы с сущностью типа `Ticket` с ID типа `Integer`.
    @Query(value = "INSERT INTO tickets  (flight_date_id, username, reserved_seats)" +
            " VALUES (?1, ?2, ?3)", nativeQuery = true) //Используем аннотацию `@Query`, которая позволяет определить SQL-запрос, который используется для вставки записи в таблицу "tickets" с указанными значениями.

    Optional<Ticket> saveTicket(Integer flightDateId, String username, Integer reservedSeats); //Этот метод объявляет операцию для сохранения нового билета. Метод возвращает `Optional`, что указывает на то, что результат операции может отсутствовать (например, если сохранение завершилось ошибкой).

}
