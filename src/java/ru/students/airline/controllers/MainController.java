package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.students.airline.dto.*;
import ru.students.airline.security.CustomAuthProvider;
import ru.students.airline.services.FlightService;
import ru.students.airline.services.OrdersService;
import ru.students.airline.services.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller // Обозначает класс как контроллер Spring MVC
@RequiredArgsConstructor // Создает конструктор для всех final полей (Lombok)
public class MainController {

    private final FlightService flightService; // Сервис для работы с рейсами
    private final OrdersService ordersService; // Сервис для работы с заказами

    @GetMapping(value = "/") // GetMapping - просто показ страницы // Обрабатывает GET запросы на корневой URL
    public ModelAndView userPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user"); // Устанавливает имя представления
        modelAndView.addObject("flightSelection", new FlightRequestDto());// передаем объект, который заполянется в html странице и возвращается обратно // Добавляет объект для выбора рейса в модель
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername());// чтобы могло отображаться справа, кто вошел на сайт // Добавляет имя пользователя в модель
        return modelAndView; // Возвращает модель и представление
    }

    @PostMapping("/") // PostMapping считывает действия пользователя и обрабатывает данные // Обрабатывает POST запросы на корневой URL
    public ModelAndView filterFlights(@ModelAttribute("flightSelection") FlightRequestDto dto, ModelAndView modelAndView) {
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername()); // Добавляет имя пользователя в модель
        modelAndView.getModel().put("flights", flightService.getSomeFlights(dto)); // Добавляет отфильтрованный список рейсов в модель
        modelAndView.setViewName("user"); // Устанавливает представление на 'user'
        return modelAndView; // Возвращает модель и представление
    }

    @PostMapping("/book") // Обрабатывает POST запросы на "/book"
    public ModelAndView buyTicket(@ModelAttribute("flightDate") String flightDate, @ModelAttribute("neededSeats") Integer neededSeats, @ModelAttribute("flightNumber") String flightNumber, ModelAndView modelAndView) {
        TicketDto ticket = new TicketDto(flightNumber, flightDate, neededSeats);
        List<PassengerDto> passengerDtoList = new ArrayList<>();
        for (int i = 0; i < neededSeats; i++) {
            passengerDtoList.add(new PassengerDto());
        }
        Optional<FlightDto> flight = flightService.getAllFlights().stream().filter((x) -> (Objects.equals(x.getNumber(), flightNumber))).findFirst();
        modelAndView.getModel().put("flightInfo", flight.get());
        modelAndView.getModel().put("passengers", passengerDtoList);
        modelAndView.getModel().put("ticket", ticket);
        OrderDto order = new OrderDto();
        modelAndView.addObject("bookRequest", order);
        modelAndView.setViewName("success");
                // Создает и добавляет данные билета и списка пассажиров в модель
        return modelAndView;  // Возвращает модель и представление
    }

    @PostMapping(value = "/bookTicket") // Обрабатывает POST запросы на "/bookTicket"
    public ModelAndView bookTicket(@ModelAttribute("bookRequest") OrderDto orderDto,
                                   @ModelAttribute("flightNumber") String flightNumber,
                                   @ModelAttribute("flightDate") String flightDate,
                                   ModelAndView modelAndView) {
                                    // Обрабатывает бронирование билета
        orderDto.setUsername(CustomAuthProvider.getAuthenticatedUsername());
        orderDto.setFlightDate(flightDate);
        ordersService.createOrder(orderDto, flightNumber);
        return userPage(modelAndView); // Возвращает обратно на главную страницу пользователя

    }

    @PostMapping(value = "bootTicket") // Обрабатывает POST запросы на "/bootTicket"
    public ModelAndView cancelBooking(ModelAndView modelAndView) {
    // Отменяет бронирование билета
        return userPage(modelAndView); // Возвращает обратно на главную страницу пользователя
    }

    @GetMapping(value = "/cab") // Обрабатывает GET запросы на "/cab"
    public ModelAndView returnCab(ModelAndView modelAndView) {
            // Возвращает пользователя в личный кабинет
        modelAndView.setViewName("cabinet");
        String username = CustomAuthProvider.getAuthenticatedUsername();
        modelAndView.getModel().put(username, CustomAuthProvider.getAuthenticatedUsername());
        modelAndView.getModel().put("orders", ordersService.getAllByUser(username));
        return modelAndView; // Возвращает модель и представление
    }

    @GetMapping(value="/orders/{id}") // Обрабатывает GET запросы на "/orders/{id}"
    public ModelAndView getOrder(@PathVariable Long id, ModelAndView modelAndView){
        // Возвращает детали заказа по ID
        modelAndView.setViewName("order");
        OrderDto order = ordersService.getOrderById(id);
        modelAndView.getModel().put("order", order);
        return modelAndView; // Возвращает модель и представление
    }
}
