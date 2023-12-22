package ru.students.airline.persistence.repository;//Указываем на принадлежность данного файла к определенному пакету в Java. Пакеты используются для организации классов в логические группы, что помогает избежать конфликтов имен и облегчает управление кодом.


import org.springframework.data.jpa.repository.Query;//Директива import, которая позволяет использовать класс Query из пакета org.springframework.data.jpa.repository в данном файле. Класс Query предоставляет аннотацию, которая используется для определения SQL-запросов напрямую в репозитории.

import org.springframework.data.repository.CrudRepository;//Добавляем возможность использовать интерфейс CrudRepository из пакета org.springframework.data.repository в текущем файле. CrudRepository предоставляет базовые методы для работы с сущностями, включая сохранение, удаление и поиск.

import org.springframework.stereotype.Repository;//Импорт аннотации @Repository из пакета org.springframework.stereotype. Эта аннотация позволяет использовать класс FlightDatesRepo как репозиторий Spring, что автоматически создает бин Spring на основе этого интерфейса.

import ru.students.airline.persistence.entity.FlightDate;//Добавляем класс FlightDate из пакета ru.students.airline.persistence.entity в текущий файл - делаем этот класс доступным для использования в данном файле. FlightDate представляет сущность (entity) для работы с датами рейсов в контексте приложения.


import java.sql.Date;//Импортируем класс Date из пакета java.sql для работы с датами в Java. Используется для представления даты рейса в репозитории.

import java.util.Optional;//Импорт класса Optional из пакета java.util. 


@Repository
public interface FlightDatesRepo extends CrudRepository<FlightDate, String> { //Объявляем интерфейс с именем FlightDatesRepo, который расширяет (extends) интерфейс CrudRepository для управления сущностями типа FlightDate с ID типа String. 
    @Query(value = "SELECT fd.* from flight_dates fd " +
            "WHERE fd.flight_number = ?1 AND fd.date = ?2" ,nativeQuery = true) //Используем аннотацию @Query, которая позволяет определить SQL-запрос напрямую в репозитории.
    Optional<FlightDate> findFlightDate(String flightNumber, Date date);// Этот метод объявляет поиск даты рейса с указанным номером рейса и датой. Метод возвращает Optional, что указывает на то, что результат поиска может отсутствовать.
}
