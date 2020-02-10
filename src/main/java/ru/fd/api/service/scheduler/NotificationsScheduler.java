/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.notification.NotificationResolver;
import ru.fd.api.service.notification.Notifier;

import java.util.List;

@Component("notificationsScheduler")
@EnableAsync
public class NotificationsScheduler {

    private final NotificationResolver<Customer> notificationResolver;
    private final Notifier<Customer> notifier;

    public NotificationsScheduler(NotificationResolver<Customer> notificationResolver, Notifier<Customer> notifier) {
        this.notificationResolver = notificationResolver;
        this.notifier = notifier;
    }

    @Async
    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
    public void a() {
//        System.out.println("Checked...");

        List<Customer> customers = notificationResolver.consumers();
        if(!customers.isEmpty()) {
            notifier.notify(customers);
        }
    }
}
