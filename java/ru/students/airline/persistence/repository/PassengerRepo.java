package ru.students.airline.persistence.repository; //Указываем на принадлежность данного файла к определенному пакету в Java.

import org.springframework.data.repository.CrudRepository; //Директивой import, которая позволяет использовать интерфейс CrudRepository из пакета org.springframework.data.repository в данном файле. 
import ru.students.airline.persistence.entity.Passenger; //Добавляем класс Passenger из пакета ru.students.airline.persistence.entity в текущий файл, делая его доступным для использования в данном файле. Passenger представляет сущность пассажира в контексте данного приложения.


public interface PassengerRepo extends CrudRepository<Passenger, Long> { //Объявляетм интерфейс с именем PassengerRepo, который наследует методы от интерфейса CrudRepository для работы с сущностью типа Passenger с ID типа Long. 
}
