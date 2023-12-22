package ru.students.airline.persistence.repository; //Указываем на принадлежность данного файла к определенному пакету в Java.

import org.springframework.data.repository.CrudRepository; //Импортируем интерфейс `CrudRepository` из пакета `org.springframework.data.repository`. `CrudRepository` предоставляет базовые методы для работы с сущностями, включая сохранение, удаление и поиск.

import ru.students.airline.persistence.entity.User; //Импортируем класс User из пакета ru.students.airline.persistence.entity, делая его доступным для использования в текущем файле или классе.

import java.util.Optional; //Импортируем класс `Optional` из пакета `java.util`. `Optional` представляет контейнер, который может содержать или отсутствовать значение  и используется для представления результатов операций, которые могут возвращать какое-то значение или ничего.

public interface UsersRepo extends CrudRepository<User, String> {  //Объявляем интерфейс `UsersRepo`, который наследует (extends) функционал от интерфейса `CrudRepository`. Работает с сущностью `User`, используя идентификатор типа `String`.
    Optional<User> findByUsername(String username);//Этот метод объявляет пользовательский метод для поиска пользователя по имени (username). Метод возвращает `Optional`, что позволяет обрабатывать возможное отсутствие результата (например, если пользователя с указанным именем не найдено).

}
