package ru.students.airline.persistence.repository;//Указываем на принадлежность данного файла к определенному пакету в Java.

import org.springframework.data.repository.CrudRepository; //Директива import, которая позволяет использовать интерфейс `CrudRepository` из пакета `org.springframework.data.repository`. 
import ru.students.airline.persistence.entity.Order; //Добавляем класс `Order` из пакета `ru.students.airline.persistence.entity` в текущий файл, делая его доступным для использования в данном файле. 
import ru.students.airline.persistence.entity.User; //Импортируем класс `User` из пакета `ru.students.airline.persistence.entity`. `User` представляет объект пользователя в контексте данного приложения.

import java.util.List; //Импортируем класс `List` из пакета `java.util`.

public interface OrdersRepo extends CrudRepository<Order, Long> { //Объявляем интерфейс с именем `OrdersRepo`, который наследует функционал от интерфейса `CrudRepository` для работы с сущностью типа `Order` с ID типа `Long`. 
    List<Order> findAllByUser(User user); //Объявляем запрос findAllByUser, который возвращает список заказов, связанных с указанным пользователем. Метод принимает объект `User` в качестве параметра и возвращает список заказов, связанных с этим пользователем.

}
